package com.panteapaliuc.movie.reviews.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String imageName;

    private String imageType;

    @Lob
    private byte[] imageByte;

    public Image(String imageName, String imageType, byte[] imageByte) {
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageByte = imageByte;
    }
}
