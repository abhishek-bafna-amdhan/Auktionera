package se.iths.auktionera.persistence.entity;

import lombok.*;
import se.iths.auktionera.business.model.User;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long auctionId;

    @ManyToOne
    private AccountEntity seller;

    @ManyToOne
    private AccountEntity buyer;

    private Instant createdAt;
    private Instant lastEditAt;
    private Double rating;
    private String reviewText;

}
