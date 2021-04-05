package com.panteapaliuc.moviereviews.repositories;

import com.panteapaliuc.moviereviews.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
