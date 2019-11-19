package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account saveAccount(Account account) {
        return null;
    }

    @Override
    public Account updateAccount(Long accountId, Account account) {
        if (accountRepository.existsById(accountId))
            return accountRepository.save(account);
        else return null;
    }

    @Override
    public Account findByEmail(String email) {
        return null;
    }

    @Override
    public void deleteAccount(Long accountId) {

    }

    @Override
    public List<Account> findAllAccounts() {
        return null;
    }

    @Override
    public List<?> getAccountStats(Long accountId) {
        return null;
    }
}
