package se.iths.auktionera.business.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.iths.auktionera.business.model.Auction;
import se.iths.auktionera.persistence.entity.AuctionEntity;
import se.iths.auktionera.persistence.repo.AuctionRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuctionService implements IAuctionService {

    private final AuctionRepo auctionRepo;

    public AuctionService(AuctionRepo auctionRepo) {
        this.auctionRepo = auctionRepo;
    }

    @Override
    public List<Auction> getAuctions(Map<String, String> filters, Map<String, String> sorters) {
        List<AuctionEntity> auctionsFound = auctionRepo.findAll();
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

//    @Override
//    public List<Auction> getAuctionsForOneAccount(Map<String, String> filters, Map<String, String> sorters, String authId) {
//        auctionRepo.findAuctionByAuthId(authId);
//        return null;
//    }

    private Sort orderBy(Map<String, String> sorters) {
        return null;
    }

}
