package com.panteapaliuc.movie.reviews.controller;

import com.panteapaliuc.movie.reviews.model.RegistrationRequest;
import com.panteapaliuc.movie.reviews.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public HttpEntity<UserDetails> register(@RequestBody RegistrationRequest request)
    {
        UserDetails user = registrationService.register(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
