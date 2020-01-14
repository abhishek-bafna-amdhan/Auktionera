package se.iths.auktionera.business.service;

import org.springframework.stereotype.Service;
import se.iths.auktionera.business.model.Auction;
import se.iths.auktionera.business.model.User;
import se.iths.auktionera.business.model.UserStats;
import se.iths.auktionera.persistence.entity.AccountEntity;
import se.iths.auktionera.persistence.entity.AuctionEntity;
import se.iths.auktionera.persistence.repo.AccountRepo;
import se.iths.auktionera.persistence.repo.AuctionRepo;

import java.util.*;

@Service
public class UserService implements IUserService{

    private final AccountRepo accountRepo;
    private final AuctionRepo auctionRepo;

    public UserService(AccountRepo accountRepo, AuctionRepo auctionRepo) {
        this.accountRepo = accountRepo;
        this.auctionRepo = auctionRepo;
    }

    @Override
    public List<User> getUsers(Map<String, String> filters, Map<String, String> sorters) {
        List<AccountEntity> accountFound = accountRepo.findAll();
        List<User> users = new ArrayList<>();
        for (AccountEntity accountEntity : accountFound) {
            users.add(User.builder().
                    id(accountEntity.getId())
                    .userName(accountEntity.getUserName())
                    .createdAt(accountEntity.getCreatedAt())
                    .stats(UserStats.builder()
                            .totalPurchases(accountEntity.getUserStats().getTotalPurchases())
                            .totalSales(accountEntity.getUserStats().getTotalSales())
                            .sellerRating(accountEntity.getUserStats().getSellerRating())
                            .buyerRating(accountEntity.getUserStats().getBuyerRating()).build())
                    .build());
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        Optional<AccountEntity> accountEntity = accountRepo.findById(id);
        User user = new User();
        accountEntity.ifPresent(entity -> {
            user.setId(entity.getId());
            user.setUserName(entity.getUserName());
            user.setCreatedAt(entity.getCreatedAt());
            user.setStats(UserStats.builder()
                    .totalPurchases(entity.getUserStats().getTotalPurchases())
                    .totalSales(entity.getUserStats().getTotalSales())
                    .sellerRating(entity.getUserStats().getSellerRating())
                    .buyerRating(entity.getUserStats().getBuyerRating())
                    .build());
        });
        return user;
    }

    @Override
    public List<Auction> getAuctionsByUser(Map<String, String> filters, Map<String, String> sorters, Long id) {
        Optional<AccountEntity> entityOptional = accountRepo.findById(id);
        AccountEntity entity = new AccountEntity();
        if (entityOptional.isPresent()) {
            entity = entityOptional.get();
        }
        List<AuctionEntity> auctionEntityList = auctionRepo.findAuctionsBySeller(entity);
        List<Auction> auctions = new ArrayList<>();
        for (AuctionEntity auctionEntity : auctionEntityList) {
            auctions.add(new Auction(auctionEntity));
        }
        return auctions;
    }

}
