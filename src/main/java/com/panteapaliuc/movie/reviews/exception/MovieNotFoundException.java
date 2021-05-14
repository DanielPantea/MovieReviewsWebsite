package com.panteapaliuc.movie.reviews.exception;

import java.util.NoSuchElementException;

public class MovieNotFoundException extends NoSuchElementException {
    public MovieNotFoundException(String message)
    {
        super(message);
    }
}
