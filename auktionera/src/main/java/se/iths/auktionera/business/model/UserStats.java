package se.iths.auktionera.business.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStats {
    private int totalSales;
    private float sellerRating;
    private float buyerRating;
}
