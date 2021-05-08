package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.exception.MovieNotFoundException;
import com.panteapaliuc.movie.reviews.model.Movie;
import com.panteapaliuc.movie.reviews.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Movie> findMoviesByTagIdList(List<Long> tagIdList){
        List<Movie> moviesByTags = movieRepository.findMoviesByMovieTagsTagId(tagIdList.iterator().next());
        for (Long tagId: tagIdList.stream().skip(1).collect(Collectors.toList())) {
            moviesByTags = moviesByTags.stream()
                    .distinct()
                    .filter(movieRepository.findMoviesByMovieTagsTagId(tagId)::contains)
                    .collect(Collectors.toList());
            movieRepository.findMoviesByMovieTagsTagId(tagId);
        }
        return moviesByTags;
    }

}
