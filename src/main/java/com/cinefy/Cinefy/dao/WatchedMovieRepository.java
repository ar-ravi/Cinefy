package com.cinefy.Cinefy.dao;

import com.cinefy.Cinefy.model.User;
import com.cinefy.Cinefy.model.WatchedMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchedMovieRepository extends JpaRepository<WatchedMovie, Long> {
    List<WatchedMovie> findByUser(User user);
}
