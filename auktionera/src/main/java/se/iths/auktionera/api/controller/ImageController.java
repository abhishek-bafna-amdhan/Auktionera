package se.iths.auktionera.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.iths.auktionera.business.model.Image;
import se.iths.auktionera.business.service.IImageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/images/")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ImageController {

    private final IImageService imageService;

    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("{id}")
    public List<Image> getImagesForAuction(@PathVariable Long id) {
        return imageService.getImagesForAuction(id);
    }

    @PostMapping(value = "upload/{id}",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Image> storeUserImage(@PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        imageService.storeImage(id, file);
        return ResponseEntity.ok(new Image(file));
    }
}
