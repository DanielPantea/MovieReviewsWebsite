package com.panteapaliuc.movie.reviews.repository;

import com.panteapaliuc.movie.reviews.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findRatingByUserUserIdAndMovieMovieId(Long userId, Long movieId);
}
