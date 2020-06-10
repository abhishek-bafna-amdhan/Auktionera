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
    private String title;
    private byte[] data;

}
