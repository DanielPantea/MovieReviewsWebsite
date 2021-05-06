package com.panteapaliuc.movie.reviews.controller;

import com.panteapaliuc.movie.reviews.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @GetMapping//(path = "/{username}")
    public HttpStatus login(/*@PathVariable("username") String username*/)
    {
        return loginService.login();
    }
}
