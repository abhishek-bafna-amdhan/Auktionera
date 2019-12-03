package se.iths.auktionera.business.model;

import lombok.*;
import se.iths.auktionera.persistence.entity.AuctionEntity;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auction {

    private long id;
    private String tags; //for the moment changed to String, should be changed into List or own Object
    private String description;
    private User seller;
    private User buyer;
    private Review sellerReview;
    private Review buyerReview;
    private AuctionState auctionState;
    private Instant endsAt;
    private Instant createdAt;
    private Instant currentBidAt;
    private Instant endedAt;
    private Integer startPrice;
    private Integer buyOutPrice;
    private Integer minBidStep;
    private Integer currentBid;
    private DeliveryType deliveryType;

    public Auction(AuctionEntity auctionEntity) {
        this.id = auctionEntity.getId();
        this.tags = auctionEntity.getTags();
        this.description = auctionEntity.getDescription();
        this.seller = User.builder()
                .id(auctionEntity.getSeller().getId())
                .userName(auctionEntity.getSeller().getUserName())
                .createdAt(auctionEntity.getSeller().getCreatedAt()).build();

        this.buyer = User.builder()
                .id(auctionEntity.getBuyer().getId())
                .userName(auctionEntity.getBuyer().getUserName())
                .createdAt(auctionEntity.getBuyer().getCreatedAt()).build();

        this.sellerReview = Review.builder().auctionId(auctionEntity.getId()).seller(this.seller)
                .reviewText(auctionEntity.getSellerReview().getReviewText())
                .buyer(this.buyer).createdAt(auctionEntity.getCreatedAt())
                .lastEditAt(auctionEntity.getSellerReview().getLastEditAt())
                .rating(auctionEntity.getSellerReview().getRating()).build();

        this.buyerReview = Review.builder().auctionId(auctionEntity.getId()).buyer(this.buyer)
                .reviewText(auctionEntity.getBuyerReview().getReviewText())
                .seller(this.seller).createdAt(auctionEntity.getCreatedAt())
                .lastEditAt(auctionEntity.getBuyerReview().getLastEditAt())
                .rating(auctionEntity.getBuyerReview().getRating()).build();

        this.auctionState = (AuctionState) auctionEntity.getAuctionState();
        this.endsAt = auctionEntity.getEndsAt();
        this.createdAt = auctionEntity.getCreatedAt();
        this.currentBidAt = auctionEntity.getCurrentBidAt();
        this.endedAt = auctionEntity.getEndedAt();
        this.startPrice = auctionEntity.getStartPrice();
        this.buyOutPrice = auctionEntity.getBuyOutPrice();
        this.minBidStep = auctionEntity.getMinBidStep();
        this.currentBid = auctionEntity.getCurrentBid();
        this.deliveryType = (DeliveryType) auctionEntity.getDeliveryType();
    }
}

