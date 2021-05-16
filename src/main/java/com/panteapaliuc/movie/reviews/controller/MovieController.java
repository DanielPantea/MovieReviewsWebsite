package com.panteapaliuc.movie.reviews.controller;

import com.panteapaliuc.movie.reviews.model.Movie;
import com.panteapaliuc.movie.reviews.model.Review;
import com.panteapaliuc.movie.reviews.service.MovieService;
import com.panteapaliuc.movie.reviews.service.RatingService;
import com.panteapaliuc.movie.reviews.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/movie")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final RatingService ratingService;
    private final ReviewService reviewService;

    @GetMapping(path = "/get/{movieId}")
    public ResponseEntity<Movie> getMovie(@PathVariable("movieId") Long movieId)
    {
        Movie movie = movieService.findMovie(movieId);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<Movie>> getMovieList()
    {
        List<Movie> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(path = "/requests/get")
    public ResponseEntity<List<Movie>> getMovieRequests()
    {
        List<Movie> movies = movieService.findAllMovieRequests();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(path = "tags/get/{tagKeys}")
    public ResponseEntity<List<Movie>> getMovieListByTags(@PathVariable("tagKeys") List<String> tagKeys)
    {
        List<Movie> movies = movieService.findMoviesByTags(tagKeys);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(path = "/rating/get/{movieId}")
    public  ResponseEntity<Float> getMovieRating(@PathVariable("movieId") Long movieId)
    {
        float rating = ratingService.getTotalMovieRating(movieId);
        return  new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping(path = "/review/get/all/{movieId}")
    public ResponseEntity<List<Review>> getMovieReviews(@PathVariable("movieId") Long movieId)
    {
        List<Review> reviews = reviewService.getAllReviewsByMovieId(movieId);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie)
    {

        Movie newMovie = movieService.addMovie(movie);

        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }

    @PostMapping("/poster/add/{movieId}")
    public ResponseEntity<Movie> addMoviePoster(@PathVariable("movieId") Long movieId, @RequestParam("posterImg") MultipartFile posterImgFile) throws IOException
    {
        Movie movie = movieService.addMoviePoster(posterImgFile, movieId);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @PutMapping("/upd")
    public ResponseEntity<Movie> updMovie(@RequestBody Movie movie)
    {
        Movie updMovie = movieService.updateMovie(movie);
        return new ResponseEntity<>(updMovie, HttpStatus.OK);
    }

    @PutMapping("/upd/enable/{movieId}")
    public ResponseEntity<?> enableMovie(@PathVariable("movieId") Long movieId)
    {
        movieService.enableMovie(movieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/del/{movieId}")
    public ResponseEntity<?> delMovie(@PathVariable("movieId") Long movieId)
    {
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
