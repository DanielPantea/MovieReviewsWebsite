package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.exception.MovieNotFoundException;
import com.panteapaliuc.movie.reviews.model.Movie;
import com.panteapaliuc.movie.reviews.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie addMovie(Movie movie)
    {
         return movieRepository.save(movie);
    }

    public List<Movie> findAllMovies()
    {
        return movieRepository.findAll();
    }

    public Movie updateMovie(Movie movie)
    {
        return movieRepository.save(movie);
    }

    public Movie findMovie(Long movieId)
    {
        return movieRepository.findMovieByMovieId(movieId)
                .orElseThrow(() -> new MovieNotFoundException(String.format("Movie with the id %d not found!", movieId)));
    }

    public void deleteMovie(Long movieId)
    {
        movieRepository.deleteMovieByMovieId(movieId);
    }

    public List<Movie> findMoviesByTagId(Long tagId){
        return movieRepository.findMoviesByMovieTagsTagId(tagId);
    }

}
