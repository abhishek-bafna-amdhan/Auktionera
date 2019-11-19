package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "Auction Site";
    }

    @RequestMapping(value = "/{accountId}/history", method = RequestMethod.GET, produces = "application/json")
    public List<?> getAllAuctions(@PathVariable("accountId") Long accountId) {
        return null;
    }

    @PutMapping(value = "/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable("accountId") Long accountId,
        @RequestBody Account accountDto) {
        Account updatedAccount = accountService.updateAccount(accountId, accountDto);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping(value = "/{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long accountId) {
        accountService.deleteAccount(accountId);
    }

    @GetMapping(value = "/{accountId}/stats")
    public List<?> getAllAccountStats(@PathVariable("accountId") Long accountId) {
        return accountService.getAccountStats(accountId);
    }

}
