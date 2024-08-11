package com.cinefy.Cinefy.dao;

import com.cinefy.Cinefy.model.Movie;
import com.cinefy.Cinefy.model.ToWatchMovie;
import com.cinefy.Cinefy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToWatchMovieRepository extends JpaRepository<ToWatchMovie, Long> {
    List<ToWatchMovie> findByUser(User user);
    Optional<ToWatchMovie> findByUserAndMovie(User user, Movie movie);
}
