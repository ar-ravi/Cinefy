package com.cinefy.Cinefy.Controller;

import com.cinefy.Cinefy.model.WatchedMovie;
import com.cinefy.Cinefy.service.WatchedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/watched")
public class WatchedMovieController {
    @Autowired
    private WatchedMovieService watchedMovieService;

    @PostMapping
    public void addWatchedMovie(@RequestBody WatchedMovie watchedMovie){
        watchedMovieService.addWatchedMovie(watchedMovie.getUser(), watchedMovie.getMovie());
    }
}
