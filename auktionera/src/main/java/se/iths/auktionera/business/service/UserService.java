package se.iths.auktionera.business.service;

import org.springframework.stereotype.Service;
import se.iths.auktionera.business.model.Auction;
import se.iths.auktionera.business.model.User;
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
        });
        return user;
    }

    @Override
    public List<Auction> getAuctionsByUser(Map<String, String> filters, Map<String, String> sorters, Long id) {
        User user = getUserById(id);
        List<AuctionEntity> auctionEntities = auctionRepo.findAllById(Collections.singleton(user.getId()));
        List<Auction> auctions = new ArrayList<>();
        for (AuctionEntity auctionEntity : auctionEntities) {
            auctions.add(new Auction(auctionEntity));
        }
        return auctions;
    }

}
