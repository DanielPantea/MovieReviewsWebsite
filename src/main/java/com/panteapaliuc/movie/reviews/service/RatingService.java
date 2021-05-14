package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.model.Movie;
import com.panteapaliuc.movie.reviews.model.Rating;
import com.panteapaliuc.movie.reviews.model.RatingRequest;
import com.panteapaliuc.movie.reviews.model.User;
import com.panteapaliuc.movie.reviews.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
@AllArgsConstructor
public class RatingService implements Serializable {

    private final RatingRepository ratingRepository;
    private final MovieService movieService;
    private final UserService userService;

    public Rating getRating(String username, Long movieId){

        User user = userService.findUserByUsername(username);
        if(ratingRepository.findRatingByUserUserIdAndMovieMovieId(user.getUserId(), movieId).isPresent())
            return ratingRepository.findRatingByUserUserIdAndMovieMovieId(user.getUserId(), movieId).get();
        else return null;
    }

    public Float getTotalMovieRating(Long movieId) {
        List<Rating> ratings = ratingRepository.findRatingsByMovieMovieId(movieId);
        Float totalGrade = 0F;
        if(ratings.stream().count() != 0)
        {
            for (Rating rating: ratings) {
                totalGrade += rating.getGrade();
            }

            totalGrade /= ratings.stream().count();
        }

        return totalGrade;
    }

    public void addRating(String username, RatingRequest ratingRequest)
    {
        User user = userService.findUserByUsername(username);
        Movie movie = movieService.findMovie(ratingRequest.getMovieId());

        if(ratingRepository.findRatingByUserUserIdAndMovieMovieId(user.getUserId(), movie.getMovieId()).isPresent())
        {
            Rating rating = ratingRepository.findRatingByUserUserIdAndMovieMovieId(user.getUserId(), movie.getMovieId()).get();
            rating.setGrade(ratingRequest.getGrade());
            ratingRepository.save(rating);
        }
        else
        {
            ratingRepository.save(
                    new Rating(
                            ratingRequest.getGrade(),
                            movie,
                            user
                    )
            );
        }
    }

    public void deleteRating(String username, Long movieId)
    {
        User user = userService.findUserByUsername(username);

        Rating rating = ratingRepository.findRatingByUserUserIdAndMovieMovieId(user.getUserId(), movieId).get();

        ratingRepository.delete(rating);
    }
}
