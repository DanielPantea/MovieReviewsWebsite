package com.panteapaliuc.movie.reviews.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @NotEmpty
    private Integer grade;

    @NotNull
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (
            name = "movieId",
            referencedColumnName = "movieId"
    )
    private Movie movie;

    @NotNull
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (
            name = "userId",
            referencedColumnName = "userId"
    )
    private User user;

    public Rating(@NotEmpty Integer grade, @NotNull Movie movie, @NotNull User user) {
        this.grade = grade;
        this.movie = movie;
        this.user = user;
    }
}
