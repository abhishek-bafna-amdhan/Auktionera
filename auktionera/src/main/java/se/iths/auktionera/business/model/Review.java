package se.iths.auktionera.business.model;

import lombok.*;
import se.iths.auktionera.persistence.entity.ReviewEntity;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    private Long reviewId;
    private User seller;
    private User buyer;
    private Instant createdAt;
    private Instant lastEditAt;
    private Double rating;
    private String reviewText;

    public Review(ReviewEntity reviewEntity) {
        this.reviewId = reviewEntity.getReviewId();
        this.seller = User.builder()
                .id(reviewEntity.getSeller().getId())
                .userName(reviewEntity.getSeller().getUserName())
                .createdAt(reviewEntity.getSeller().getCreatedAt()).build();

        if (reviewEntity.getBuyer() != null) {
            this.buyer = User.builder()
                    .id(reviewEntity.getBuyer().getId())
                    .userName(reviewEntity.getBuyer().getUserName())
                    .createdAt(reviewEntity.getBuyer().getCreatedAt()).build();
        }

        this.createdAt = reviewEntity.getCreatedAt();
        this.lastEditAt = reviewEntity.getLastEditAt();
        this.rating = reviewEntity.getRating();
        this.reviewText = reviewEntity.getReviewText();
    }
}
