package se.iths.auktionera.persistence.entity;

import lombok.*;
import se.iths.auktionera.business.model.AuctionState;
import se.iths.auktionera.business.model.DeliveryType;
import se.iths.auktionera.business.model.Review;
import se.iths.auktionera.business.model.User;

import javax.persistence.*;
import java.util.Date;
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
    private List<String> tags;
    private String description;
    private User seller;
    private User buyer;
    private Review sellerReview;
    private Review buyerReview;
    private AuctionState auctionState;
    private Date endsAt;
    private Date createdAt;
    private Date currentBidAt;
    private Date endedAt;
    private Integer startPrice;
    private Integer buyOutPrice;
    private Integer minBidStep;
    private Integer currentBid;
    private DeliveryType deliveryType;
}






