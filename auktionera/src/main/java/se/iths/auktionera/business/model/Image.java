package se.iths.auktionera.business.model;

import lombok.*;
import se.iths.auktionera.persistence.entity.ImageEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    private String description;
    private byte[] data;

    public Image(ImageEntity i) {
        this.description = i.getDescription();
        this.data = i.getData();
    }
}
