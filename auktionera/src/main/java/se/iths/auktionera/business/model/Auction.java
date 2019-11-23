package se.iths.auktionera.business.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auction {

    private long id;
    //"tags" : ["string"],
    private String description;
    private User seller;
//            "buyer" : User,
//            "sellerReview" : Review,
//            "buyerReview" : Review,
//            "state" : enum:AuctionState,
//            "endsAt" : Date,
//            "createdAt" : Date,
//            "currentBidAt" : Date,
//            "endedAt" : Date,
//            "startPrice" : 0,
//            "buyoutPrice" : 0,
//            "minBidStep" : 0,
//            "currentBid" : 0,
//            "deliveryType" : enum:DeliveryType
}
