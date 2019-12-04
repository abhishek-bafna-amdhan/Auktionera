package se.iths.auktionera.business.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
public class Bid {
    private int bid;
    private boolean isValidBid;
    private Integer startPrice;
    private Integer buyOutPrice;
    private Integer minBidStep;
    private Integer currentBid;
}
