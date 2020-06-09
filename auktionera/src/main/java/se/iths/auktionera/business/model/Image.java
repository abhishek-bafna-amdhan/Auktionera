package se.iths.auktionera.business.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import se.iths.auktionera.persistence.entity.ImageEntity;

import java.io.IOException;

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

    public Image(MultipartFile file) throws IOException {
        this.description = file.getName();
        this.data = file.getBytes();
    }
}
