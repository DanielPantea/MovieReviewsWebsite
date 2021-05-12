package com.panteapaliuc.movie.reviews.controller;

import com.panteapaliuc.movie.reviews.model.RatingRequest;
import com.panteapaliuc.movie.reviews.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path = "api/rating")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addRating(@RequestBody RatingRequest ratingRequest)
    {
        // Get the logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ratingService.addRate(username, ratingRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/del/{movieId}")
    public ResponseEntity<?> delRating(@PathVariable("movieId") Long movieId)
    {
        // Get the logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ratingService.deleteRating(username, movieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
