package com.panteapaliuc.movie.reviews.controller;

import com.panteapaliuc.movie.reviews.model.*;
import com.panteapaliuc.movie.reviews.service.MovieService;
import com.panteapaliuc.movie.reviews.service.RatingService;
import com.panteapaliuc.movie.reviews.service.ReviewService;
import com.panteapaliuc.movie.reviews.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final RatingService ratingService;
    private final ReviewService reviewService;
    private final MovieService movieService;

    @GetMapping(path = "/watchlist/get")
    public ResponseEntity<List<Movie>> getUserWatchlist()
    {
        // Get the logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Movie> watchlist = userService.findWatchlistByUsername(username);

        return new ResponseEntity<>(watchlist, HttpStatus.OK);
    }

    @GetMapping(path = "/diary/get")
    public ResponseEntity<List<Movie>> getUserDiary()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Movie> watchlist = userService.findDiaryByUsername(username);

        return new ResponseEntity<>(watchlist, HttpStatus.OK);
    }

    @GetMapping(path = "review/get/all/{movieId}")
    public ResponseEntity<List<Review>> getUserReviewsByMovie(@PathVariable("movieId") Long movieId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Review> reviews = reviewService.getUserReviewsByMovieId(username, movieId);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping(path = "/rating/get/{movieId}")
    public ResponseEntity<Integer> getRating(@PathVariable("movieId") Long movieId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Rating rating = ratingService.getRating(username, movieId);

        return new ResponseEntity<>(rating != null ? rating.getGrade() : 0, HttpStatus.OK);
    }

    @PostMapping(path = "/watchlist/add/{movieId}")
    public ResponseEntity<?> addToWatchlist(@PathVariable("movieId") Long movieId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.addMovieToWatchlist(username, movieId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/diary/add/{movieId}")
    public ResponseEntity<?> addToDiary(@PathVariable("movieId") Long movieId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.addMovieToDiary(username, movieId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/review/add")
    public ResponseEntity<?> addReview(@RequestBody ReviewRequest reviewRequest)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        reviewService.addReview(username, reviewRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/rating/add")
    public ResponseEntity<?> addRating(@RequestBody RatingRequest ratingRequest)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ratingService.addRating(username, ratingRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/movie/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie)
    {
        if(movie.getIsEnabled())
            movie.setIsEnabled(false);

        Movie newMovie = movieService.addMovie(movie);

        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/watchlist/del/{movieId}")
    public ResponseEntity<?> delFromWatchlist(@PathVariable("movieId") Long movieId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.removeMovieFromWatchlist(username, movieId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/diary/del/{movieId}")
    public ResponseEntity<?> delFromDiary(@PathVariable("movieId") Long movieId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.removeMovieFromDiary(username, movieId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(path = "/rating/del/{movieId}")
    public ResponseEntity<?> delRating(@PathVariable("movieId") Long movieId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ratingService.deleteRating(username, movieId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
