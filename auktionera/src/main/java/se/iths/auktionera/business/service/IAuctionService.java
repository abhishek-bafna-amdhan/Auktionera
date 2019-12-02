package se.iths.auktionera.business.service;

import se.iths.auktionera.business.model.Auction;
import se.iths.auktionera.business.model.AuctionRequest;

import java.util.List;
import java.util.Map;

public interface IAuctionService {
    List<Auction> getAuctions(Map<String, String> filters, Map<String, String> sorters);

    Auction getAuctionById(long id);

    Auction createAuction(String authId, AuctionRequest auctionRequest);

    //List<Auction> getAuctionsForOneAccount(Map<String, String> filters, Map<String, String> sorters, String authId);
}
