package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.model.Movie;
import com.panteapaliuc.movie.reviews.model.User;
import com.panteapaliuc.movie.reviews.model.UserInfo;
import com.panteapaliuc.movie.reviews.repository.UserInfoRepository;
import com.panteapaliuc.movie.reviews.repository.UserRepository;
import com.panteapaliuc.movie.reviews.utility.enUserRole;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
//@AllArgsConstructor
public class UserService implements UserDetailsService {

    public UserService(UserRepository userRepository, UserInfoRepository userInfoRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MovieService movieService) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.movieService = movieService;

        User user = new User(
                "Daniel",
                bCryptPasswordEncoder.encode("12345"),
                "daniel@upt",
                Collections.emptyList(),
                Collections.emptyList(),
                enUserRole.ADMIN
        );
        user = userRepository.save(user);
        UserInfo userInfo = userInfoRepository.save( new UserInfo(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getUserRole()
        ));
        user.setUserInfo(userInfo);
        userRepository.save(user);
    }

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
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

        user = userRepository.save(user);

        user.setUserInfo(userInfoRepository.save(
                new UserInfo(
                        user.getUserId(),
                        user.getUsername(),
                        user.getEmail(),
                        enUserRole.ADMIN
                )
        ));
        user = userRepository.save(user);
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
