package com.panteapaliuc.movie.reviews.controller;

import com.panteapaliuc.movie.reviews.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private static final List<Movie> MOVIES = Arrays.asList(
            new Movie(1L, "Avengers"),
            new Movie(2L, "Star Wars"),
            new Movie(3L, "Titanic")
    );

    @GetMapping(path = "{movieId}")
    public Movie getMovie(@PathVariable("movieId") Long movieId)
    {
        return MOVIES.stream().filter(movie -> movieId.equals(movie.getMovieId())).findFirst()
                .orElseThrow(() -> new IllegalStateException("Movie " + movieId + " does not exist"));
    }

    @GetMapping(path = "all")
    public List<Movie> getMovieList()
    {
        return MOVIES;
    }
}
