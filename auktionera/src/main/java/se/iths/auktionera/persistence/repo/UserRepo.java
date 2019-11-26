package se.iths.auktionera.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.auktionera.business.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
}
