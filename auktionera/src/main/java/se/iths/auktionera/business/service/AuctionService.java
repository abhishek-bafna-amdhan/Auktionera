package se.iths.auktionera.business.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.iths.auktionera.business.model.*;
import se.iths.auktionera.persistence.entity.AccountEntity;
import se.iths.auktionera.persistence.entity.AuctionEntity;
import se.iths.auktionera.persistence.entity.CategoryEntity;
import se.iths.auktionera.persistence.entity.UserStatsEntity;
import se.iths.auktionera.persistence.repo.AccountRepo;
import se.iths.auktionera.persistence.repo.AuctionRepo;
import se.iths.auktionera.persistence.repo.CategoryRepo;
import se.iths.auktionera.persistence.repo.UserStatsRepo;

import java.time.Instant;
import java.util.*;

@Service
public class AuctionService implements IAuctionService {

    private final AuctionRepo auctionRepo;
    private final AccountRepo accountRepo;
    private final UserStatsRepo userStatsRepo;
    private final CategoryRepo categoryRepo;

    public AuctionService(AuctionRepo auctionRepo, AccountRepo accountRepo, UserStatsRepo userStatsRepo, CategoryRepo categoryRepo) {
        this.auctionRepo = auctionRepo;
        this.accountRepo = accountRepo;
        this.userStatsRepo = userStatsRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Auction> getAuctions(Map<String, String> filters, Map<String, String> sorters) {
        List<AuctionEntity> auctionsFound = auctionRepo.findAllByAuctionState(AuctionState.INPROGRESS);
        List<Auction> auctionsToReturn = new ArrayList<>();
        for (AuctionEntity auctionEntity : auctionsFound) {
            auctionsToReturn.add(new Auction(auctionEntity));
        }
        return auctionsToReturn;
    }

    @Override
    public Auction getAuctionById(long id) {
        Optional<AuctionEntity> optionalAuction = auctionRepo.findById(id);
        AuctionEntity auctionEntity = optionalAuction.orElseThrow();
        return new Auction(auctionEntity);
    }

    @Override
    public Auction createAuction(String authId, AuctionRequest auctionRequest) {
        AccountEntity seller = accountRepo.findByAuthId(authId);
        AuctionEntity auctionToBeCreated = AuctionEntity.builder()
                .description(auctionRequest.getDescription())
                .tags(auctionRequest.getTags())
                .seller(seller)
                .auctionState(AuctionState.INPROGRESS)
                .endsAt(auctionRequest.getEndsAt())
                .createdAt(Instant.now())
                .startPrice(auctionRequest.getStartPrice())
                .buyOutPrice(auctionRequest.getBuyoutPrice())
                .minBidStep(auctionRequest.getMinBidStep())
                .deliveryType(auctionRequest.getDeliveryType()).build();
        CategoryEntity currentCategory = categoryRepo.findByCategory(auctionRequest.getCategory());
        auctionToBeCreated.setCategory(currentCategory);
        currentCategory.getAuctions().add(auctionToBeCreated);
        auctionRepo.saveAndFlush(auctionToBeCreated);
        categoryRepo.saveAndFlush(currentCategory);
        seller.getAuctionEntities().add(auctionToBeCreated);
        accountRepo.saveAndFlush(seller);
        return new Auction(auctionToBeCreated);
    }

    @Override
    public void deleteAuctionById(Long id, String authId) {
        AuctionEntity auctionEntity = auctionRepo.findById(id).orElseThrow();
        AccountEntity acc = Objects.requireNonNull(accountRepo.findByAuthId(authId));
        acc.getAuctionEntities().remove(auctionEntity);
        accountRepo.saveAndFlush(acc);
        auctionRepo.deleteById(id);
        auctionRepo.flush();
    }

    @Override
    public Auction addBidToAuction(Bid bid, Long id, String authId) {
//        private Integer startPrice;
//        private Integer buyOutPrice;
//        private Integer minBidStep;
//        private Integer currentBid;
        Optional<AuctionEntity> optionalAuction = auctionRepo.findById(id);
        AuctionEntity auctionEntity = optionalAuction.orElseThrow();
        Auction auction = new Auction(auctionEntity);
        AccountEntity acc = Objects.requireNonNull(accountRepo.findByAuthId(authId));
        if (bid.getBid() < auction.getStartPrice() ||
                bid.getBid() <= auction.getCurrentBid()) throw new IllegalArgumentException("Cannot bid lower than start price: " +
                auction.getStartPrice() + " and current bid: " + auction.getCurrentBid());
        else {
            if (bid.getBid() == auction.getBuyOutPrice()) {
                Optional.of(bid.getBid()).ifPresent(auctionEntity::setCurrentBid);
                Optional.of(acc).ifPresent(auctionEntity::setBuyer);
                auctionEntity.setEndedAt(Instant.now());
                auctionEntity.setAuctionState(AuctionState.ENDEDBOUGHT);
                AuctionEntity updatedAuction = auctionRepo.saveAndFlush(auctionEntity);
                updateUserStats(updatedAuction);
                return new Auction(updatedAuction);
            }
            Optional.of(bid.getBid()).ifPresent(auctionEntity::setCurrentBid);
            auctionEntity.setCurrentBidAt(Instant.now());
            AuctionEntity updatedAuction = auctionRepo.saveAndFlush(auctionEntity);
            return new Auction(updatedAuction);
        }
    }

    private void updateUserStats(AuctionEntity updatedAuction) {
        UserStatsEntity sellerStats = userStatsRepo.findById(updatedAuction.getSeller().getId()).orElseThrow();
        sellerStats.setTotalSales(sellerStats.getTotalSales() + 1);
        UserStatsEntity buyerStats = userStatsRepo.findById(updatedAuction.getBuyer().getId()).orElseThrow();
        buyerStats.setTotalPurchases(buyerStats.getTotalPurchases() + 1);
        userStatsRepo.saveAndFlush(sellerStats);
        userStatsRepo.saveAndFlush(buyerStats);

    }

    @Override
    public List<Auction> getAuctionsForOneAccount(Map<String, String> filters, Map<String, String> sorters, String authId) {
        AccountEntity accountEntity = accountRepo.findByAuthId(authId);
        List<AuctionEntity> auctionsFound = accountEntity.getAuctionEntities();
        List<Auction> auctionsToReturn = new ArrayList<>();
        for (AuctionEntity auctionEntity : auctionsFound) {
            auctionsToReturn.add(new Auction(auctionEntity));
            System.out.println(auctionEntity.getDescription());
        }
        return auctionsToReturn;
    }

    private Sort orderBy(Map<String, String> sorters) {
        return null;
    }

}
