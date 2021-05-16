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
    private Long movieId;

    @NotNull
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (
            name = "userId",
            referencedColumnName = "userId"
    )
    private UserInfo user;

    public Rating(@NotEmpty Integer grade, @NotNull Long movieId, @NotNull UserInfo user) {
        this.grade = grade;
        this.movieId = movieId;
        this.user = user;
    }
}
