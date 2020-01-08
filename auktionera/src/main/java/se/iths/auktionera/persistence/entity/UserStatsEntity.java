package se.iths.auktionera.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user_stats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int totalSales;
    private int totalPurchases;
    private double sellerRating;
    private double buyerRating;

    @ManyToMany
    @JoinTable(name = "stats_reviews")
    private Set<ReviewEntity> reviewEntities = new HashSet<>();

    @OneToOne
    private AccountEntity account;

    public UserStatsEntity(ReviewEntity reviewToSave, AccountEntity account) {
        this.account = account;
        this.sellerRating = calculateSellerAverage(account.getId());
        this.buyerRating = calculateBuyerAverage(account.getId());
        this.totalPurchases++;
        this.totalSales++;
        this.reviewEntities.add(reviewToSave);
    }

    @Transient
    private Double calculateSellerAverage(Long id){
        List<Double> sum = new ArrayList<>();
        for (ReviewEntity r: reviewEntities
             ) {
            if (r.getSeller().getId() == id) {
                sum.add(r.getRating());
            }
        }
        OptionalDouble average = sum.stream().mapToDouble(a -> a).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

    @Transient
    private Double calculateBuyerAverage(Long id){
        List<Double> sum = new ArrayList<>();
        for (ReviewEntity r: reviewEntities
        ) {
            if (r.getBuyer().getId() == id) {
                sum.add(r.getRating());
            }
        }
        OptionalDouble average = sum.stream().mapToDouble(a -> a).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }
}
