package se.iths.auktionera.business.service;

import org.springframework.stereotype.Service;
import se.iths.auktionera.business.model.Account;
import se.iths.auktionera.business.model.User;
import se.iths.auktionera.persistence.entity.AccountEntity;
import se.iths.auktionera.persistence.repo.AccountRepo;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService{

    private final AccountRepo accountRepo;

    public UserService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public List<User> getUsers(Map<String, String> filters, Map<String, String> sorters) {
        List<AccountEntity> accountFound = accountRepo.findAll();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < accountFound.size() ; i++) {
            users.add(User.builder().
                    id(accountFound.get(i).getId())
                    .userName(accountFound.get(i).getUserName())
                    .createdAt(accountFound.get(i).getCreatedAt())
                    .build());
        }
        return users;
    }

    @Override
    public User getUserById(String authId) {
        AccountEntity accountEntity = accountRepo.findByAuthId(authId);
        User user = User.builder()
                .id(accountEntity.getId())
                .userName(accountEntity.getUserName())
                .createdAt(accountEntity.getCreatedAt())
                .build();
        return user;
    }
//
//    @Override
//    public List<User> getAuctionsByUser(Map<String, String> filters, Map<String, String> sorters, String authId) {
//        List<AccountEntity> accountEntities = accountRepo.findAllById();
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < accountFound.size() ; i++) {
//            users.add(User.builder().
//                    id(accountFound.get(i).getId())
//                    .userName(accountFound.get(i).getUserName())
//                    .createdAt(accountFound.get(i).getCreatedAt())
//                    .build());
//        }
//        return users;


}
