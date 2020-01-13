package se.iths.auktionera.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.auktionera.persistence.entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByCategory(String category);
}
