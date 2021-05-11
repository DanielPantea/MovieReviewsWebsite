package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.model.Movie;
import com.panteapaliuc.movie.reviews.model.User;
import com.panteapaliuc.movie.reviews.repository.UserRepository;
import com.panteapaliuc.movie.reviews.utility.enUserRole;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MovieService movieService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByUsername(username);
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

    public User findUserByUsername(String username)
    {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with the username %s not found!", username)));
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
