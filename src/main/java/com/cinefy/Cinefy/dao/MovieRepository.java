package com.cinefy.Cinefy.dao;

import com.cinefy.Cinefy.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAll();
    List<Movie>findByGenres_Name(String genreName);
}
