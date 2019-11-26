package se.iths.auktionera.business.model;

import lombok.*;
import se.iths.auktionera.persistence.entity.ReviewEntity;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    private Long auctionId;
    private User seller;
    private User buyer;
    private Date createdAt;
    private Date lastEditAt;
    private Double rating;
    private String reviewText;

    public Review(ReviewEntity reviewEntity) {
        this.auctionId = reviewEntity.getAuctionId();
        this.seller = User.builder()
                .id(reviewEntity.getSeller().getId())
                .userName(reviewEntity.getSeller().getUserName())
                .createdAt(reviewEntity.getSeller().getCreatedAt()).build();

        this.buyer = User.builder()
                .id(reviewEntity.getBuyer().getId())
                .userName(reviewEntity.getBuyer().getUserName())
                .createdAt(reviewEntity.getBuyer().getCreatedAt()).build();

        this.createdAt = reviewEntity.getCreatedAt();
        this.lastEditAt = reviewEntity.getLastEditAt();
        this.rating = reviewEntity.getRating();
        this.reviewText = reviewEntity.getReviewText();
    }
}
