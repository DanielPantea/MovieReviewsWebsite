package com.panteapaliuc.movie.reviews.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ReviewRequest {
    private final Long movieId;
    private final String reviewText;
}
