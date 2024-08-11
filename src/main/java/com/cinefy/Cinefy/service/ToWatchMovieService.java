package com.cinefy.Cinefy.service;

import com.cinefy.Cinefy.dao.ToWatchMovieRepository;
import com.cinefy.Cinefy.model.Movie;
import com.cinefy.Cinefy.model.ToWatchMovie;
import com.cinefy.Cinefy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ToWatchMovieService {
    @Autowired
    private ToWatchMovieRepository toWatchMovieRepository;

    public void addToWatchMovie(User user, Movie movie) {
        try {
            ToWatchMovie toWatchMovie = new ToWatchMovie();
            toWatchMovie.setUser(user);
            toWatchMovie.setMovie(movie);
            System.out.println("Saving ToWatchMovie: User - " + user.getUsername() + ", Movie - " + movie.getTitle());
            toWatchMovieRepository.save(toWatchMovie);
        } catch (Exception e) {
            System.out.println("Failed to save ToWatchMovie: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public List<Movie> getToWatchMoviesForUser(User user){
        List<ToWatchMovie> toWatchMovies = toWatchMovieRepository.findByUser(user);
        return toWatchMovies.stream()
                .map(ToWatchMovie::getMovie)
                .collect(Collectors.toList());
    }
}
