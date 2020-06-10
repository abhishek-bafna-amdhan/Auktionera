package se.iths.auktionera.business.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.iths.auktionera.api.exception.NotFoundException;
import se.iths.auktionera.business.model.Image;
import se.iths.auktionera.persistence.entity.AuctionEntity;
import se.iths.auktionera.persistence.entity.ImageEntity;
import se.iths.auktionera.persistence.repo.AuctionRepo;
import se.iths.auktionera.persistence.repo.ImageRepo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService implements IImageService {

    private final AuctionRepo auctionRepo;
    private final ImageRepo imageRepo;

    public ImageService(AuctionRepo auctionRepo, ImageRepo imageRepo) {
        this.auctionRepo = auctionRepo;
        this.imageRepo = imageRepo;
    }


    @Override
    public List<Image> getImagesForAuction(Long id) {
        AuctionEntity auctionEntity = auctionRepo.findById(id).orElseThrow(() -> new NotFoundException("No auction with id: " +
                id + " was found. Please insert a valid auction id."));

        return auctionEntity.getImages().stream().map(i -> new Image(i.getDescription(), i.getTitle(), i.getData())).collect(Collectors.toList());
    }

    @Override
    public Image storeImage(Long id, MultipartFile file) throws IOException {
        isFileEmpty(file);
        isImage(file);

        AuctionEntity auctionEntity = auctionRepo.findById(id).orElseThrow(() -> new NotFoundException("No auction with id: " +
                id + " was found. Please insert a valid auction id."));
        ImageEntity imageEntity = ImageEntity
                .builder()
                .data(file.getBytes())
                .contentType(file.getContentType())
                .description(file.getName())
                .title(file.getOriginalFilename())
                .auction(auctionEntity)
                .build();
        imageRepo.saveAndFlush(imageEntity);
        auctionEntity.getImages().add(imageEntity);
        auctionRepo.saveAndFlush(auctionEntity);
        return null;
    }

    private void isImage(MultipartFile file) {
        if (!Arrays.asList(
                "image/jpg",
                "image/jpeg",
                "image/png",
                "image/gif").contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
        }
    }
}
