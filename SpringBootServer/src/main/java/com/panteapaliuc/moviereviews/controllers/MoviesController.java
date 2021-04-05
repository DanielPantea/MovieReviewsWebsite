package com.panteapaliuc.moviereviews.controllers;

import com.panteapaliuc.moviereviews.models.Movie;
import com.panteapaliuc.moviereviews.services.MoviesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {
    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getMovieList()
    {
        List<Movie> movies = moviesService.findMovieList();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie)
    {
        Movie newMovie = moviesService.addMovie(movie);
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }
}
