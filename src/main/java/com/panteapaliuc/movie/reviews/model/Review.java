package com.panteapaliuc.movie.reviews.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String reviewText;

    @OneToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public Review(String reviewText, Movie movie, User user) {
        this.reviewText = reviewText;
        this.movie = movie;
        this.user = user;
    }
}
