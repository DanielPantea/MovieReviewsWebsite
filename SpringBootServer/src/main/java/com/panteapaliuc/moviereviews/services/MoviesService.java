package com.panteapaliuc.moviereviews.services;

import com.panteapaliuc.moviereviews.models.Movie;
import com.panteapaliuc.moviereviews.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesService {
    private final MovieRepository movieRepository;

    @Autowired
    public MoviesService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findMovieList()
    {
        return movieRepository.findAll();
    }
    public Movie addMovie(Movie movie)
    {
        return movieRepository.save(movie);
    }

}
