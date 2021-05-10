package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.model.User;
import com.panteapaliuc.movie.reviews.model.Watchlist;
import com.panteapaliuc.movie.reviews.repository.UserRepository;
import com.panteapaliuc.movie.reviews.utility.enUserRole;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final WatchlistService watchlistService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, WatchlistService watchlistService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.watchlistService = watchlistService;

        userRepository.save(
                new User(
                        "daniel",
                        this.bCryptPasswordEncoder.encode("12345"),
                        "daniel.pantea@student.upt.ro",
                        watchlistService.addWatchlist(new Watchlist()),
                        enUserRole.ADMIN
                )
        );

        userRepository.save(
                new User(
                        "filip",
                        this.bCryptPasswordEncoder.encode("12345"),
                        "filip.paliuc@student.upt.ro",
                        watchlistService.addWatchlist(new Watchlist()),
                        enUserRole.ADMIN
                )
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with the username %s not found!", username)));
    }

    public HttpStatus register(User user)
    {
        boolean usernameTaken = userRepository.findByUsername(user.getUsername()).isPresent();

        if(usernameTaken)
            throw new IllegalStateException("Username taken");

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return HttpStatus.OK;
    }
}
