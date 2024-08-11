package com.cinefy.Cinefy.Controller;

import com.cinefy.Cinefy.dto.MovieDTO;
import com.cinefy.Cinefy.model.Genre;
import com.cinefy.Cinefy.model.Movie;
import com.cinefy.Cinefy.model.ToWatchMovie;
import com.cinefy.Cinefy.service.GenreService;
import com.cinefy.Cinefy.service.MovieService;
import com.cinefy.Cinefy.service.ToWatchMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ToWatchMovieService toWatchMovieService;

    @Autowired
    private GenreService genreService;

    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre){
        return movieService.getMoviesByGenre(genre);
    }

    @PostMapping
    public Movie addMovie(@ModelAttribute("movieDTO") MovieDTO movieDTO){
        List<String>genreNames = Arrays.asList(movieDTO.getGenres().split("\\s*,\\s*"));

        List<Genre> genres = genreService.getOrCreateGenres(genreNames);
        Movie movie = new Movie(movieDTO.getTitle(), genres, movieDTO.getYear(), movieDTO.getRating());
        return movieService.addMovie(movie);

    }
}
