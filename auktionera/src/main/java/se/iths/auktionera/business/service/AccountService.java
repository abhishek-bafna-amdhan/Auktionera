package se.iths.auktionera.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.auktionera.api.exception.ExistingUsernameException;
import se.iths.auktionera.business.model.Account;
import se.iths.auktionera.business.model.AccountRequest;
import se.iths.auktionera.business.model.UserDTO;
import se.iths.auktionera.persistence.entity.AccountEntity;
import se.iths.auktionera.persistence.entity.UserStatsEntity;
import se.iths.auktionera.persistence.repo.AccountRepo;
import se.iths.auktionera.persistence.repo.UserStatsRepo;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    private final AccountRepo accountRepo;

    private final UserStatsRepo userStatsRepo;

    private final PasswordEncoder bcryptEncoder;

    public AccountService(AccountRepo accountRepo, UserStatsRepo userStatsRepo, PasswordEncoder bcryptEncoder) {
        this.accountRepo = accountRepo;
        this.userStatsRepo = userStatsRepo;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public Account getAccount(String userName) {
        AccountEntity acc = accountRepo.findByUserName(userName);
        return new Account(acc);
    }


    @Override
    public Account updateAccount(String userName, AccountRequest accountRequest) {
        AccountEntity acc = Objects.requireNonNull(accountRepo.findByUserName(userName));

        Optional.ofNullable(accountRequest.getUserName()).ifPresent(acc::setUserName);
        Optional.ofNullable(accountRequest.getEmail()).ifPresent(acc::setEmail);
        Optional.ofNullable(accountRequest.getStreetName()).ifPresent(acc::setStreetName);
        Optional.ofNullable(accountRequest.getCity()).ifPresent(acc::setCity);
        Optional.ofNullable(accountRequest.getPostNr()).ifPresent(acc::setPostNr);
        Optional.ofNullable(accountRequest.getAnonymousBuyer()).ifPresent(acc::setAnonymousBuyer);

        AccountEntity updated = accountRepo.saveAndFlush(acc);
        return new Account(updated);
    }

    public AccountEntity createAccount(UserDTO user) throws ExistingUsernameException {
        if (accountRepo.findByUserName(user.getUsername()) == null) {
            AccountEntity newUser = new AccountEntity();
            newUser.setUserName(user.getUsername());
            newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
            newUser.setCreatedAt(Instant.now());
            UserStatsEntity use = new UserStatsEntity();
            newUser.setUserStats(use);
            userStatsRepo.saveAndFlush(use);
            accountRepo.saveAndFlush(newUser);
            return newUser;
        } else {
            throw new ExistingUsernameException("This username already exists. Try another.");
        }
    }
}
