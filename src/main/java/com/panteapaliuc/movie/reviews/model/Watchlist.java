package com.panteapaliuc.movie.reviews.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchlistId;

    @ManyToMany
    @JoinTable(
            name = "watchlistsMovies",
            joinColumns = @JoinColumn(name = "watclistId"),
            inverseJoinColumns = @JoinColumn(name = "movieId")
    )
    private Set<Movie> movies;
}
