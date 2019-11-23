package se.iths.auktionera.business.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String streetName;
    private int postNr;
    private String city;
}
