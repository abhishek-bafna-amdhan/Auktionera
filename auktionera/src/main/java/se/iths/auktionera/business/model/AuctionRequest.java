package se.iths.auktionera.business.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AuctionRequest {
    private String tags;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Instant endsAt;

    private int startPrice;

    private int buyoutPrice;

    private int minBidStep;

    private DeliveryType deliveryType;

    private List<Image> images;
}
