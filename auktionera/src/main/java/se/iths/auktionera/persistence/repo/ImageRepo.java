package se.iths.auktionera.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.auktionera.persistence.entity.ImageEntity;

public interface ImageRepo extends JpaRepository<ImageEntity, Long> {
}
