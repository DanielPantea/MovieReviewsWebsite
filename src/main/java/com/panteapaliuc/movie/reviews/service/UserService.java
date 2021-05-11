package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.model.Movie;
import com.panteapaliuc.movie.reviews.model.User;
import com.panteapaliuc.movie.reviews.repository.UserRepository;
import com.panteapaliuc.movie.reviews.utility.enUserRole;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MovieService movieService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MovieService movieService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.movieService = movieService;

        userRepository.save(
                new User(
                        "daniel",
                        this.bCryptPasswordEncoder.encode("12345"),
                        "daniel.pantea@student.upt.ro",
                        Collections.emptyList(),
                        Collections.emptyList(),
                        enUserRole.ADMIN
                )
        );

        userRepository.save(
                new User(
                        "filip",
                        this.bCryptPasswordEncoder.encode("12345"),
                        "filip.paliuc@student.upt.ro",
                        Collections.emptyList(),
                        Collections.emptyList(),
                        enUserRole.ADMIN
                )
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with the username %s not found!", username)));
    }

    public UserDetails register(User user)
    {
        boolean usernameTaken = userRepository.findByUsername(user.getUsername()).isPresent();

        if(usernameTaken)
            throw new IllegalStateException("Username taken");

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return user;
    }

    public List<Movie> findWatchlistByUsername(String username)
    {
        return userRepository.findByUsername(username).get().getWatchlist();
    }

    public List<Movie> findDiaryByUsername(String username)
    {
        return userRepository.findByUsername(username).get().getDiary();
    }

    public void addMovieToWatchlist(String username, Long movieId)
    {
        User user = userRepository.findByUsername(username).get();
        Movie movie = movieService.findMovie(movieId);

        if(!user.getWatchlist().contains(movie))
        {
            user.getWatchlist().add(movie);
            userRepository.save(user);
        }
    }
    public void removeMovieFromWatchlist(String username, Long movieId)
    {
        User user = userRepository.findByUsername(username).get();
        Movie movie = movieService.findMovie(movieId);

        if(user.getWatchlist().contains(movie))
        {
            user.getWatchlist().remove(movie);
            userRepository.save(user);
        }
    }

    public void addMovieToDiary(String username, Long movieId)
    {
        User user = userRepository.findByUsername(username).get();
        Movie movie = movieService.findMovie(movieId);

        if(!user.getDiary().contains(movie))
        {
            user.getDiary().add(movie);
            userRepository.save(user);
        }
    }
    public void removeMovieFromDiary(String username, Long movieId)
    {
        User user = userRepository.findByUsername(username).get();
        Movie movie = movieService.findMovie(movieId);

        if(user.getDiary().contains(movie))
        {
            user.getDiary().remove(movie);
            userRepository.save(user);
        }
    }
}
