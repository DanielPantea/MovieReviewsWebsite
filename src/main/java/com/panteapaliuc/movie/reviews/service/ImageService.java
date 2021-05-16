package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.model.Image;
import com.panteapaliuc.movie.reviews.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image addImage(Image image)
    {
        return imageRepository.save(image);
    }
}
