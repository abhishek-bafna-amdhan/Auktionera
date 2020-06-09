package se.iths.auktionera.business.service;

import org.springframework.web.multipart.MultipartFile;
import se.iths.auktionera.business.model.Image;

import java.io.IOException;
import java.util.List;

public interface IImageService {

    List<Image> getImagesForAuction(Long id);

    Image storeImage(Long id, MultipartFile file) throws IOException;
}
