package com.cinefy.Cinefy.service;

import com.cinefy.Cinefy.dao.WatchedMovieRepository;
import com.cinefy.Cinefy.model.Movie;
import com.cinefy.Cinefy.model.User;
import com.cinefy.Cinefy.model.WatchedMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchedMovieService {

    @Autowired
    private WatchedMovieRepository watchedMovieRepository;

    public void addWatchedMovie(User user, Movie movie, Integer rating){
        WatchedMovie watchedMovie = new WatchedMovie();
        watchedMovie.setUser(user);
        watchedMovie.setMovie(movie);
        watchedMovie.setRating(rating);
        watchedMovieRepository.save(watchedMovie);
    }
}
