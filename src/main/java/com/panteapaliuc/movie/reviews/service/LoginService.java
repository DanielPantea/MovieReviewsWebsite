package com.panteapaliuc.movie.reviews.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserService userService;

    public HttpStatus login()
    {
        return HttpStatus.OK;
    }
}
