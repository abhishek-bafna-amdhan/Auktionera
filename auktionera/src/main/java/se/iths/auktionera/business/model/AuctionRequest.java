package se.iths.auktionera.business.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.iths.auktionera.api.config.InstantDeserializer;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AuctionRequest {
    // todo add the tags/category field and decide whether or not to make it a class
    private String tags;
    private String description;

    @JsonDeserialize(using = InstantDeserializer.class)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Instant endsAt;

    private int startPrice;

    private int buyoutPrice;

    private int minBidStep;

    private DeliveryType deliveryType;

    private List<Image> images;
}
