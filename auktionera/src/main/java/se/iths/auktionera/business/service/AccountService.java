package se.iths.auktionera.business.service;

import org.springframework.stereotype.Service;
import se.iths.auktionera.business.model.Account;
import se.iths.auktionera.business.model.AccountRequest;
import se.iths.auktionera.persistence.entity.AccountEntity;
import se.iths.auktionera.persistence.repo.AccountRepo;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    private final AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Account getAccount(String authId) {
        AccountEntity acc = accountRepo.findByAuthId(authId);
        if (acc == null) {
            acc = accountRepo.saveAndFlush(AccountEntity.builder().authId(authId).createdAt(Instant.now()).build());
        }
        return new Account(acc);
    }


    @Override
    public Account updateAccount(String authId, AccountRequest accountRequest) {
        AccountEntity acc = Objects.requireNonNull(accountRepo.findByAuthId(authId));

        Optional.ofNullable(accountRequest.getUserName()).ifPresent(acc::setUserName);
        Optional.ofNullable(accountRequest.getEmail()).ifPresent(acc::setEmail);
        Optional.ofNullable(accountRequest.getStreetName()).ifPresent(acc::setStreetName);
        Optional.ofNullable(accountRequest.getCity()).ifPresent(acc::setCity);
        Optional.ofNullable(accountRequest.getPostNr()).ifPresent(acc::setPostNr);
        Optional.ofNullable(accountRequest.getAnonymousBuyer()).ifPresent(acc::setAnonymousBuyer);

        AccountEntity updated = accountRepo.saveAndFlush(acc);
        return new Account(updated);
    }
}
