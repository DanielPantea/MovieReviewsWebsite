package com.panteapaliuc.movie.reviews.repository;

import com.panteapaliuc.movie.reviews.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Long> {

    void deleteMovieByMovieId(Long id);

    Optional<Movie> findMovieByMovieId(Long movieId);
}
