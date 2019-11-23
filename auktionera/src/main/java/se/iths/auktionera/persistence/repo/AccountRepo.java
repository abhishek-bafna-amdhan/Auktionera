package se.iths.auktionera.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.auktionera.persistence.entity.AccountEntity;

public interface AccountRepo extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByAuthId(String authId);
}
