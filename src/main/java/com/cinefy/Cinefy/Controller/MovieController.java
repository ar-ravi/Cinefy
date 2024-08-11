package com.cinefy.Cinefy.Controller;

import com.cinefy.Cinefy.dao.WatchedMovieRepository;
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

    @Autowired
    private WatchedMovieService watchedMovieService;

    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre){
        return movieService.getMoviesByGenre(genre);
    }

    public User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            user = userDetails.getUser();
        }
        if(user == null){
            System.out.println("ISSUE LIES IN FETCHING USER HERE.");
        }
        return user;
    }

    @PostMapping
    public String addMovie(@ModelAttribute("movieDTO") MovieDTO movieDTO){
        List<String>genreNames = Arrays.asList(movieDTO.getGenres().split("\\s*,\\s*"));

        List<Genre> genres = genreService.getOrCreateGenres(genreNames);
        Movie movie = new Movie(movieDTO.getTitle(), genres, movieDTO.getYear(), movieDTO.getRating());
        movieService.addMovie(movie);
        User user = getUser();
        toWatchMovieService.addToWatchMovie(user, movie);
        return "redirect:/dashboard";
    }
    @PostMapping("/moveToWatched")
    public String moveToWatched(@RequestParam Long movieId){
        User currentUser = getUser();
        movieService.moveToWatched(currentUser, movieId);
        return "redirect:/dashboard";
    }

    @PostMapping("/removeFromToWatch")
    public String removeMovie(@RequestParam Long movieId){
        Movie movie = movieService.getMovieById(movieId);
        User user = getUser();
        movieService.deleteMovie(user, movieId);
        return "redirect:/dashboard";
    }
}
