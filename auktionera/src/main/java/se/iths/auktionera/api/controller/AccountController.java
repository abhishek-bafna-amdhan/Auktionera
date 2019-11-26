package se.iths.auktionera.api.controller;

import org.springframework.web.bind.annotation.*;
import se.iths.auktionera.business.model.Account;
import se.iths.auktionera.business.model.AccountRequest;
import se.iths.auktionera.business.model.Auction;
import se.iths.auktionera.business.service.IAccountService;
import se.iths.auktionera.business.service.IAuctionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    private final IAccountService accountService;

    private final IAuctionService auctionService;

    public AccountController(IAccountService accountService, IAuctionService auctionService) {
        this.accountService = accountService;
        this.auctionService = auctionService;
    }

    @GetMapping("api/account")
    public Account getAccount(HttpServletRequest request) {
        return accountService.getAccount((String) request.getAttribute("authId"));
    }

    @PatchMapping("api/account")
    public Account updateAccount(@Valid @RequestBody AccountRequest fields, HttpServletRequest request) {
        return accountService.updateAccount((String) request.getAttribute("authId"), fields);
    }

    @GetMapping("api/account/auctions")
    public List<Auction> getAllAuctionsForAccount(@RequestParam Map<String, String> filter, @RequestParam Map<String, String> sort, HttpServletRequest request) {
        return auctionService.getAuctionsForOneAccount(filter, sort, (String) request.getAttribute("authId"));
    }
}
