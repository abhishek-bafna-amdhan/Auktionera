package se.iths.auktionera.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.auktionera.persistence.entity.RoleEntity;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRole(String role);
}
