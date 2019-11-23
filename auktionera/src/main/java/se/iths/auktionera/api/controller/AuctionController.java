package se.iths.auktionera.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.iths.auktionera.business.model.Auction;
import se.iths.auktionera.business.service.IAuctionService;

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
}
