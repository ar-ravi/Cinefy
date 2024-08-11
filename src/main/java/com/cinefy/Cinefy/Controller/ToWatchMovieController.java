package com.cinefy.Cinefy.Controller;

import com.cinefy.Cinefy.model.ToWatchMovie;
import com.cinefy.Cinefy.service.ToWatchMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/to-watch")
public class ToWatchMovieController {

    @Autowired
    private ToWatchMovieService toWatchMovieService;

    @PostMapping
    public void addToWatchMovie(@RequestBody ToWatchMovie toWatchMovie){
        toWatchMovieService.addToWatchMovie(toWatchMovie.getUser(), toWatchMovie.getMovie());
    }
}
