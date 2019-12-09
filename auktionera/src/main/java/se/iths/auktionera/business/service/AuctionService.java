package se.iths.auktionera.business.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.iths.auktionera.business.model.*;
import se.iths.auktionera.persistence.entity.AccountEntity;
import se.iths.auktionera.persistence.entity.AuctionEntity;
import se.iths.auktionera.persistence.repo.AccountRepo;
import se.iths.auktionera.persistence.repo.AuctionRepo;

import java.time.Instant;
import java.util.*;

@Service
public class AuctionService implements IAuctionService {

    private final AuctionRepo auctionRepo;
    private final AccountRepo accountRepo;

    public AuctionService(AuctionRepo auctionRepo, AccountRepo accountRepo) {
        this.auctionRepo = auctionRepo;
        this.accountRepo = accountRepo;
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
        auctionRepo.saveAndFlush(auctionToBeCreated);
        return new Auction(auctionToBeCreated);
    }

    @Override
    public void deleteAuctionById(long id) {
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
                bid.getBid() <= auction.getCurrentBid()) throw new IllegalArgumentException("Cannot bid lower than start price and current bid.");
        else {
            if (bid.getBid() == auction.getBuyOutPrice()) {
                Optional.of(bid.getBid()).ifPresent(auctionEntity::setCurrentBid);
                Optional.of(acc).ifPresent(auctionEntity::setBuyer);
                auctionEntity.setEndedAt(Instant.now());
                auctionEntity.setAuctionState(AuctionState.ENDEDBOUGHT);
                AuctionEntity updatedAuction = auctionRepo.saveAndFlush(auctionEntity);
                return new Auction(updatedAuction);
            }
            Optional.of(bid.getBid()).ifPresent(auctionEntity::setCurrentBid);
            auctionEntity.setCurrentBidAt(Instant.now());
            AuctionEntity updatedAuction = auctionRepo.saveAndFlush(auctionEntity);
            return new Auction(updatedAuction);
        }
    }

//    @Override
//    public List<Auction> getAuctionsForOneAccount(Map<String, String> filters, Map<String, String> sorters, String authId) {
//        auctionRepo.findAuctionByAuthId(authId);
//        return null;
//    }

    private Sort orderBy(Map<String, String> sorters) {
        return null;
    }

}
