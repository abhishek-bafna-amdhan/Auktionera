package se.iths.auktionera.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.iths.auktionera.business.model.Image;
import se.iths.auktionera.business.service.IImageService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ImageController {

    private final IImageService imageService;

    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("api/images/{id}")
    public List<Image> getImagesForAuction(@PathVariable Long id) {
        return imageService.getImagesForAuction(id);
    }

    @PostMapping("api/images/upload/{id}")
    public ResponseEntity.BodyBuilder storeUserImage(@PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        imageService.storeImage(id, file);
        return ResponseEntity.ok();
    }
}
