package com.cinefy.Cinefy.Controller;

import com.cinefy.Cinefy.dto.MovieDTO;
import com.cinefy.Cinefy.model.Genre;
import com.cinefy.Cinefy.model.Movie;
import com.cinefy.Cinefy.model.ToWatchMovie;
import com.cinefy.Cinefy.model.User;
import com.cinefy.Cinefy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ToWatchMovieService toWatchMovieService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre){
        return movieService.getMoviesByGenre(genre);
    }

    @PostMapping
    public String addMovie(@ModelAttribute("movieDTO") MovieDTO movieDTO){
        List<String>genreNames = Arrays.asList(movieDTO.getGenres().split("\\s*,\\s*"));

        List<Genre> genres = genreService.getOrCreateGenres(genreNames);
        Movie movie = new Movie(movieDTO.getTitle(), genres, movieDTO.getYear(), movieDTO.getRating());
        movieService.addMovie(movie);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println(username);
        User user = (User)userDetailsService.loadUserByUsername(username);
//        System.out.println(user);

//        toWatchMovieService.addToWatchMovie(user, movie);
        return "redirect:/dashboard";

    }
}
