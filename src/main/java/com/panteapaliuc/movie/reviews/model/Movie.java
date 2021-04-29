package com.panteapaliuc.movie.reviews.model;

public class Movie {

    private final Long movieId;
    private final String movieTitle;

    public Movie(Long movieId, String movieTitle) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
    }

    public Long getMovieId() {
        return movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
}
