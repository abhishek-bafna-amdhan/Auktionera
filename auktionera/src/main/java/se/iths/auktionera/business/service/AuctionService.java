package se.iths.auktionera.business.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.iths.auktionera.business.model.Auction;
import se.iths.auktionera.persistence.repo.AuctionRepo;

import java.util.List;
import java.util.Map;

@Service
public class AuctionService implements IAuctionService {

    private final AuctionRepo auctionRepo;

    public AuctionService(AuctionRepo auctionRepo) {
        this.auctionRepo = auctionRepo;
    }

    @Override
    public List<Auction> getAuctions(Map<String, String> filters, Map<String, String> sorters) {
        return null;
    }

    @Override
    public List<Auction> getAuctionsForOneAccount(Map<String, String> filters, Map<String, String> sorters, String authId) {
        auctionRepo.findAuctionByAuthId(authId);
        return null;
    }

    private Sort orderBy(Map<String, String> sorters) {
        return null;
    }

}
