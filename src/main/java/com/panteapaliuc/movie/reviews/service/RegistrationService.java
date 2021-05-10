package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.model.RegistrationRequest;
import com.panteapaliuc.movie.reviews.model.User;
import com.panteapaliuc.movie.reviews.model.Watchlist;
import com.panteapaliuc.movie.reviews.utility.enUserRole;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService  {

    private final UserService userService;
    private EmailValidatorService emailValidatorService;
    private WatchlistService watchlistService;

    public HttpStatus register(RegistrationRequest request)
    {
        boolean isValid = emailValidatorService.test(request.getEmail());

        if(!isValid)
            throw new IllegalStateException("Email not valid");

        return userService.register(
                new User(
                        request.getUsername(),
                        request.getPassword(),
                        request.getEmail(),
                        watchlistService.addWatchlist(new Watchlist()),
                        enUserRole.USER
                )
        );
    }
}
