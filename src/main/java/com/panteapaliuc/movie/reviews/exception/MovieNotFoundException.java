package com.panteapaliuc.movie.reviews.exception;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String message)
    {
        super(message);
    }
}
