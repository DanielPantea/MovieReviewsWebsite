package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.exception.MovieNotFoundException;
import com.panteapaliuc.movie.reviews.model.Movie;
import com.panteapaliuc.movie.reviews.model.Tag;
import com.panteapaliuc.movie.reviews.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final TagService tagService;

    public Movie addMovie(Movie movie)
    {
        for (Tag tag:movie.getMovieTags()) {
            if(!tagService.checkTagExists(tag))
                tagService.addTag(tag);
        }
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

    public List<Movie> findMoviesByTags(List<String> tagKeys){
        List<Movie> moviesByTags = movieRepository.findMoviesByMovieTagsTagKey(tagKeys.iterator().next());
        for (String tagKey: tagKeys.stream().skip(1).collect(Collectors.toList())) {
            moviesByTags = moviesByTags.stream()
                    .distinct()
                    .filter(movieRepository.findMoviesByMovieTagsTagKey(tagKey)::contains)
                    .collect(Collectors.toList());
            movieRepository.findMoviesByMovieTagsTagKey(tagKey);
        }
        return moviesByTags;
    }


}
