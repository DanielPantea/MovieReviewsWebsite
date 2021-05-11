package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserService userService;

    public UserDetails login(String username)
    {
        return userService.loadUserByUsername(username);
    }
}
