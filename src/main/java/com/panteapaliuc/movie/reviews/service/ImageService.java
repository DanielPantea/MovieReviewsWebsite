package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.model.Image;
import com.panteapaliuc.movie.reviews.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image addImage(Image image)
    {
        return imageRepository.save(image);
    }

    public void delImage(Image image)
    {
        imageRepository.delete(image);
    }
}
