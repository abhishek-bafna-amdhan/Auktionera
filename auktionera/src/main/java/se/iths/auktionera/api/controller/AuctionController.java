package se.iths.auktionera.api.controller;

import org.springframework.web.bind.annotation.*;
import se.iths.auktionera.business.model.Auction;
import se.iths.auktionera.business.model.AuctionRequest;
import se.iths.auktionera.business.model.Bid;
import se.iths.auktionera.business.service.IAuctionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class AuctionController {

    private final IAuctionService auctionService;

    public AuctionController(IAuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("api/auctions")
    public List<Auction> getAuctions(@RequestParam Map<String, String> filter, @RequestParam Map<String, String> sort) {
        return auctionService.getAuctions(filter, sort);
    }

    @GetMapping("api/auctions/{id}")
    public Auction getAuctionById(@PathVariable Long id) {
        return auctionService.getAuctionById(id);
    }

    @PostMapping("api/auctions")
    public Auction createAuction(@RequestBody AuctionRequest fields, HttpServletRequest request) {
        return auctionService.createAuction((String) request.getAttribute("authId"), fields);
    }

    @PostMapping("api/auctions/{id}/bid")
    public Auction createBid(@RequestBody Bid bid, @PathVariable Long id, HttpServletRequest request) {
        return auctionService.addBidToAuction(bid, id, (String) request.getAttribute("authId"));
    }

    @DeleteMapping("api/auctions/{id}")
    public void deleteAuction(@PathVariable Long id) {
        auctionService.deleteAuctionById(id);
    }
}
