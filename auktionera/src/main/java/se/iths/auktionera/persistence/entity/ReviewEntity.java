package se.iths.auktionera.persistence.entity;

import lombok.*;
import se.iths.auktionera.business.model.User;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEntity {

    private Long auctionId;
    private User seller;
    private User buyer;
    private Date createdAt;
    private Date lastEditAt;
    private Double rating;
    private String reviewText;

}
