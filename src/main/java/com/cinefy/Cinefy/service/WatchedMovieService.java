package com.cinefy.Cinefy.service;

import com.cinefy.Cinefy.dao.WatchedMovieRepository;
import com.cinefy.Cinefy.model.Movie;
import com.cinefy.Cinefy.model.User;
import com.cinefy.Cinefy.model.WatchedMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchedMovieService {

    @Autowired
    private WatchedMovieRepository watchedMovieRepository;

    public void addWatchedMovie(User user, Movie movie){
        WatchedMovie watchedMovie = new WatchedMovie();
        watchedMovie.setUser(user);
        watchedMovie.setMovie(movie);
        watchedMovieRepository.save(watchedMovie);
    }

    public List<Movie> getMoviesByUser(User user){
        return watchedMovieRepository.findByUser(user)
                .stream().map(WatchedMovie::getMovie).collect(Collectors.toList());
    }


}
