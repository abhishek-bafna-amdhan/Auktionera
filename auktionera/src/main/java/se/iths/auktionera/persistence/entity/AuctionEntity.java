package se.iths.auktionera.persistence.entity;

import lombok.*;
import se.iths.auktionera.business.model.AuctionState;
import se.iths.auktionera.business.model.DeliveryType;
import se.iths.auktionera.business.model.Review;
import se.iths.auktionera.business.model.User;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String tags;
    private String description;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private AccountEntity seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private AccountEntity buyer;

    @OneToOne
    @JoinColumn(name = "sellerReview_id")
    private ReviewEntity sellerReview;

    @OneToOne
    @JoinColumn(name = "buyerReview_id")
    private ReviewEntity buyerReview;
    private Enum auctionState;
    private Instant endsAt;
    private Instant createdAt;
    private Instant currentBidAt;
    private Instant endedAt;
    private int startPrice;
    private int buyOutPrice;
    private int minBidStep;
    private int currentBid;
    private Enum deliveryType;
}






