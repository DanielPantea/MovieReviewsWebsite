package com.panteapaliuc.movie.reviews.controller;

import com.panteapaliuc.movie.reviews.model.RegistrationRequest;
import com.panteapaliuc.movie.reviews.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/register")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public HttpStatus register(@RequestBody RegistrationRequest request)
    {
        return registrationService.register(request);
    }
}
