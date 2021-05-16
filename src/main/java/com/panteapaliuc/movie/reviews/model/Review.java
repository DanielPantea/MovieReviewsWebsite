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

    private Long movieId;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserInfo user;

    public Review(String reviewText, Date postDate, Long movieId, UserInfo user) {
        this.reviewText = reviewText;
        this.postDate = postDate;
        this.movieId = movieId;
        this.user = user;
    }
}
