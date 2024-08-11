package com.cinefy.Cinefy.service;

import com.cinefy.Cinefy.dao.ToWatchMovieRepository;
import com.cinefy.Cinefy.model.Movie;
import com.cinefy.Cinefy.model.ToWatchMovie;
import com.cinefy.Cinefy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToWatchMovieService {
    @Autowired
    private ToWatchMovieRepository toWatchMovieRepository;
    public void addToWatchMovie(User user, Movie movie){
        ToWatchMovie toWatchMovie = new ToWatchMovie();
        toWatchMovie.setUser(user);
        toWatchMovie.setMovie(movie);
        toWatchMovieRepository.save(toWatchMovie);
    }
}
