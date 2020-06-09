package se.iths.auktionera.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private String contentType;

    @Column(length = 2000, columnDefinition="mediumblob")
    private byte[] data;

    @ManyToOne
    private AuctionEntity auction;
}
