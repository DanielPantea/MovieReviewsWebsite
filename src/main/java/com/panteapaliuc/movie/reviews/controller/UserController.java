package com.panteapaliuc.movie.reviews.controller;

import com.panteapaliuc.movie.reviews.model.Movie;
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

    @GetMapping(path = "/watchlist")
    public ResponseEntity<List<Movie>> getUserWatchlist()
    {
        // Get the logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Movie> watchlist = userService.findWatchlistByUsername(username);

        return new ResponseEntity<>(watchlist, HttpStatus.OK);
    }

    @PostMapping(path = "/watchlist/add/{movieId}")
    public ResponseEntity<?> addToWatchlist(@PathVariable("movieId") Long movieId)
    {
        // Get the logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.addMovieToWatchlist(username, movieId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/watchlist/del/{movieId}")
    public ResponseEntity<?> delFromWatchlist(@PathVariable("movieId") Long movieId)
    {
        // Get the logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.removeMovieFromWatchlist(username, movieId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
