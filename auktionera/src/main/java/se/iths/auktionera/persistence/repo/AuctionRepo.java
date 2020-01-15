package se.iths.auktionera.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import se.iths.auktionera.business.model.AuctionState;
import se.iths.auktionera.persistence.entity.AccountEntity;
import se.iths.auktionera.persistence.entity.AuctionEntity;

import java.util.List;
import java.util.Optional;

public interface AuctionRepo extends JpaRepository<AuctionEntity, Long>, JpaSpecificationExecutor {
    List<AuctionEntity> findAuctionsBySeller(AccountEntity seller);

    List<AuctionEntity> findAllByAuctionState(Enum auctionState);
    Optional<AuctionEntity> findByIdAndAuctionState(Long id, AuctionState state);

}
