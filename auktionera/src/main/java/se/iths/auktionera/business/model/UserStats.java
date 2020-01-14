package se.iths.auktionera.business.model;

import lombok.*;
import se.iths.auktionera.persistence.entity.UserStatsEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStats {
    private int totalSales;
    private int totalPurchases;
    private double sellerRating;
    private double buyerRating;
    private String reviewComments;

    public UserStats(UserStatsEntity ent){
        this.totalSales = ent.getTotalSales();
        this.totalPurchases = ent.getTotalPurchases();
        this.sellerRating = ent.getSellerRating();
        this.buyerRating = ent.getBuyerRating();
    }
}
