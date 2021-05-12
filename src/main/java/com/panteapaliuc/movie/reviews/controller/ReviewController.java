package com.panteapaliuc.movie.reviews.controller;

import com.panteapaliuc.movie.reviews.model.Movie;
import com.panteapaliuc.movie.reviews.model.Review;
import com.panteapaliuc.movie.reviews.model.ReviewRequest;
import com.panteapaliuc.movie.reviews.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/review")
@AllArgsConstructor

public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping(path = "all/{movieId}")
    public ResponseEntity<List<Review>> getReviewsByMovie(@PathVariable("movieId") Long movieId)
    {
        List<Review> reviews = reviewService.getAllReviewsByMovieId(movieId);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping(path = "user/{movieId}")
    public ResponseEntity<List<Review>> getUserReviewsByMovie(@PathVariable("movieId") Long movieId)
    {
        // Get the logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Review> reviews = reviewService.getUserReviewsByMovieId(username, movieId);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping(path = "add")
    public ResponseEntity<?> addReview(@RequestBody ReviewRequest reviewRequest)
    {
        // Get the logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        reviewService.addReview(username, reviewRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
