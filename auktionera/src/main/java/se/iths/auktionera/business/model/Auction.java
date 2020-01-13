package se.iths.auktionera.business.model;

import lombok.*;
import org.springframework.lang.Nullable;
import se.iths.auktionera.persistence.entity.AuctionEntity;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auction {

    private long auctionId;
    private String category;
    private String tags; //for the moment changed to String, should be changed into List or own Object
    private String description;
    private User seller;

    @Nullable
    private User buyer;

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
        this.auctionId = auctionEntity.getId();
        this.category = auctionEntity.getCategory().getCategoryTitle();
        this.tags = auctionEntity.getTags();
        this.description = auctionEntity.getDescription();
        this.seller = User.builder()
                .id(auctionEntity.getSeller().getId())
                .userName(auctionEntity.getSeller().getUserName())
                .createdAt(auctionEntity.getSeller().getCreatedAt())
                .stats(UserStats.builder()
                        .totalSales(auctionEntity.getSeller().getUserStats().getTotalSales())
                        .totalPurchases(auctionEntity.getSeller().getUserStats().getTotalPurchases())
                        .buyerRating(auctionEntity.getSeller().getUserStats().getBuyerRating())
                        .sellerRating(auctionEntity.getSeller().getUserStats().getSellerRating())
                        .build())
                .build();

        if (auctionEntity.getBuyer() != null) {
            this.buyer = User.builder()
                    .id(auctionEntity.getBuyer().getId())
                    .userName(auctionEntity.getBuyer().getUserName())
                    .createdAt(auctionEntity.getBuyer().getCreatedAt())
                    .stats(UserStats.builder()
                            .totalSales(auctionEntity.getBuyer().getUserStats().getTotalSales())
                            .totalPurchases(auctionEntity.getBuyer().getUserStats().getTotalPurchases())
                            .buyerRating(auctionEntity.getBuyer().getUserStats().getBuyerRating())
                            .sellerRating(auctionEntity.getBuyer().getUserStats().getSellerRating())
                            .build())
                    .build();
        }

        this.auctionState = auctionEntity.getAuctionState();
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

