package com.panteapaliuc.movie.reviews.controller;

import com.panteapaliuc.movie.reviews.model.User;
import com.panteapaliuc.movie.reviews.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @GetMapping
    public HttpEntity<UserDetails> login()
    {
        // Get the logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails user = loginService.login(username);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}
