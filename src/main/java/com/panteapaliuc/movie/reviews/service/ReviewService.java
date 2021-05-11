package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.model.Movie;
import com.panteapaliuc.movie.reviews.model.Review;
import com.panteapaliuc.movie.reviews.model.ReviewRequest;
import com.panteapaliuc.movie.reviews.model.User;
import com.panteapaliuc.movie.reviews.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieService movieService;
    private final UserService userService;

    public ReviewService(ReviewRepository reviewRepository, MovieService movieService, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.movieService = movieService;
        this.userService = userService;
    }

    public List<Review> getAllReviewsByMovieId(Long movieId)
    {
        return reviewRepository.findReviewsByMovieMovieId(movieId);
    }

    public void addReview(String username, ReviewRequest reviewRequest)
    {
        User user = userService.findUserByUsername(username);
        Movie movie = movieService.findMovie(reviewRequest.getMovieId());

        reviewRepository.save(
                new Review(
                        reviewRequest.getReviewText(),
                        movie,
                        user
                )
        );
    }
}
