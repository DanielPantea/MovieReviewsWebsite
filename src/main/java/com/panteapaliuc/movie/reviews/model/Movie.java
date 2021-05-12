package com.panteapaliuc.movie.reviews.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

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

    @Lob
    private String movieDesc;

    private String posterImgUrl;

    private Date releaseDate;

    private String trailerUrl;

    private Integer lengthMinutes;

    private String movieDirectors;

    private String movieWriters;

    private String movieActors;

    private Boolean isEnabled;

    @ManyToMany
    @JoinTable(
            name = "moviesTags",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "tagKey")
    )
    private Set<Tag> movieTags;

    public Movie(String movieTitle,
                 String movieDesc,
                 String posterImgUrl,
                 Date releaseDate,
                 String trailerUrl,
                 Integer lengthMinutes,
                 String movieDirectors,
                 String movieWriters,
                 String movieActors,
                 Set<Tag> movieTags,
                 Boolean isEnabled) {
        this.movieTitle = movieTitle;
        this.movieDesc = movieDesc;
        this.posterImgUrl = posterImgUrl;
        this.releaseDate = releaseDate;
        this.trailerUrl = trailerUrl;
        this.lengthMinutes = lengthMinutes;
        this.movieDirectors = movieDirectors;
        this.movieWriters = movieWriters;
        this.movieActors = movieActors;
        this.movieTags = movieTags;
        this.isEnabled = isEnabled;
    }
}
