package se.iths.auktionera.business.service;

import org.springframework.stereotype.Service;
import se.iths.auktionera.business.model.Review;
import se.iths.auktionera.business.model.ReviewRequest;
import se.iths.auktionera.persistence.entity.AccountEntity;
import se.iths.auktionera.persistence.entity.AuctionEntity;
import se.iths.auktionera.persistence.entity.ReviewEntity;
import se.iths.auktionera.persistence.repo.AccountRepo;
import se.iths.auktionera.persistence.repo.AuctionRepo;
import se.iths.auktionera.persistence.repo.ReviewRepo;

import java.time.Instant;
import java.util.Optional;

@Service
public class ReviewService implements IReviewService {
    private final AuctionRepo auctionRepo;
    private final ReviewRepo reviewRepo;
    private final AccountRepo accountRepo;

    public ReviewService(AuctionRepo auctionRepo, ReviewRepo reviewRepo, AccountRepo accountRepo) {
        this.auctionRepo = auctionRepo;
        this.reviewRepo = reviewRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public Review createReview(ReviewRequest incomingReview, Long id) {
        Optional<AuctionEntity> entityOptional = auctionRepo.findById(id);
        AuctionEntity auctionEntity = new AuctionEntity();
//        Optional<AccountEntity> accountOptional = accountRepo.findById(auctionEntity.getSeller().getId());
//        AccountEntity accountEntity = accountOptional.get();
        if (entityOptional.isPresent()) auctionEntity = entityOptional.get();
        ReviewEntity reviewToSave = ReviewEntity.builder()
                .seller(auctionEntity.getSeller())
                .buyer(auctionEntity.getBuyer())
                .reviewText(incomingReview.getReviewText())
                .rating(incomingReview.getRating())
                .createdAt(Instant.now())
                .lastEditAt(Instant.now())
                .build();

        reviewRepo.saveAndFlush(reviewToSave);
        auctionEntity.setSellerReview(reviewToSave);
        auctionEntity.getSeller().getReviewEntities().add(reviewToSave);
        auctionRepo.saveAndFlush(auctionEntity);

//        accountEntity.getReviewEntities().add(reviewToSave);
//        accountRepo.saveAndFlush(accountEntity);
        return new Review(reviewToSave);
    }
}
