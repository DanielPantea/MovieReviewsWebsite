package com.panteapaliuc.movie.reviews.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String reviewText;

    private Date postDate;

    @OneToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public Review(String reviewText, Date postDate, Movie movie, User user) {
        this.reviewText = reviewText;
        this.postDate = postDate;
        this.movie = movie;
        this.user = user;
    }
}
