package se.iths.auktionera.persistence.entity;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(unique = true, nullable = false, updatable = false)
    private String authId;

    private String userName = StringUtils.EMPTY;

    private String email = StringUtils.EMPTY;
    private boolean anonymousBuyer;
    private Instant createdAt;
    private String streetName = StringUtils.EMPTY;
    private int postNr;
    private String city = StringUtils.EMPTY;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AuctionEntity> auctionEntities = new HashSet<>();

    @OneToMany
    private Set<ReviewEntity> reviewEntities = new HashSet<>();
}
