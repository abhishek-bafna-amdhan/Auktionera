package se.iths.auktionera.persistence.entity;

import lombok.*;
import se.iths.auktionera.business.model.User;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEntity {

    @Id
    @Column(name = "reviewId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;

    @ManyToOne
    private AccountEntity seller;

    @ManyToOne
    private AccountEntity buyer;

    @ManyToMany(mappedBy = "reviews")
    private List<AuctionEntity> auctions;

    private Instant createdAt;
    private Instant lastEditAt;
    private Double rating;
    private String reviewText;

}