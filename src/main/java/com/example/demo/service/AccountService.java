package com.example.demo.service;

import com.example.demo.model.Account;

import java.util.List;

public interface AccountService {

    Account saveAccount(Account account);

    Account updateAccount(Long accountId, Account account);

    Account findByEmail(String email);

    void deleteAccount(Long accountId);

    List<Account> findAllAccounts();

    List<?> getAccountStats(Long accountId);
}
