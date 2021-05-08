package com.panteapaliuc.movie.reviews.controller;

import com.panteapaliuc.movie.reviews.model.Movie;
import com.panteapaliuc.movie.reviews.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("api/movie")
@AllArgsConstructor
public class MovieController {

    private MovieService movieService;

    @GetMapping(path = "/{movieId}")
    public ResponseEntity<Movie> getMovie(@PathVariable("movieId") Long movieId)
    {
        Movie movie = movieService.findMovie(movieId);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Movie>> getMovieList()
    {
        List<Movie> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(path = "/tag/{tagId}")
    public ResponseEntity<List<Movie>> getMovieListByTag(@PathVariable("tagId") Long tagId)
    {
        List<Movie> movies = movieService.findMoviesByTagId(tagId);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie)
    {
        Movie newMovie = movieService.addMovie(movie);
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }

    @PutMapping("/upd")
    public ResponseEntity<Movie> updMovie(@RequestBody Movie movie)
    {
        Movie updMovie = movieService.updateMovie(movie);
        return new ResponseEntity<>(updMovie, HttpStatus.OK);
    }

    @DeleteMapping("/del/{movieId}")
    public ResponseEntity<?> delMovie(@PathVariable("movieId") Long movieId)
    {
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
