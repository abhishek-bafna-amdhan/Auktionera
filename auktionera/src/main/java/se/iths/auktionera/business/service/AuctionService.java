package se.iths.auktionera.business.service;

import org.springframework.stereotype.Service;
import se.iths.auktionera.business.model.Auction;

import java.util.List;
import java.util.Map;

@Service
public class AuctionService implements IAuctionService {
    @Override
    public List<Auction> getAuctions(Map<String, String> filters, Map<String, String> sorters) {
        return null;
    }
}
