package com.panteapaliuc.movie.reviews.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @NotBlank(message = "Title is required")
    private String movieTitle;

    private String movieDesc;

    private String posterImgUrl;

    private Date releaseDate;

    private String trailerUrl;

    private Integer lengthMinutes;

    private String movieDirectors;

    private String movieWriters;

    private String movieActors;

    @ManyToMany
    private List<Tag> tags;

    public Movie(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
