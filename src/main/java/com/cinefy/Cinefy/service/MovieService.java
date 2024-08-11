package com.cinefy.Cinefy.service;

import com.cinefy.Cinefy.dao.GenreRepository;
import com.cinefy.Cinefy.dao.MovieRepository;
import com.cinefy.Cinefy.dao.ToWatchMovieRepository;
import com.cinefy.Cinefy.dao.WatchedMovieRepository;
import com.cinefy.Cinefy.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ToWatchMovieRepository toWatchMovieRepository;

    @Autowired
    private WatchedMovieRepository watchedMovieRepository;

    @Autowired
    private GenreRepository genreRepository;

    public List<Movie> getMoviesByGenre(String genreName){
        Genre genre = genreRepository.findByName(genreName).get();
        if(genre != null){
            return genre.getMovies();
        } else{
            return List.of();
        }
    }

    public Movie addMovie(Movie movie){
        List<Genre> genres = movie.getGenres().stream()
                .map(genre -> genreRepository.findByName(genre.getName()).isEmpty() ? genreRepository.save(genre): genreRepository.findByName(genre.getName()).get())
                .collect(Collectors.toList());
        movie.setGenres(genres);
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id " + id));
    }

    public Movie updateMovie(Long id, Movie updateMovie){
        Movie movie = getMovieById(id);
        movie.setTitle(updateMovie.getTitle());
        movie.setReleaseYear(updateMovie.getReleaseYear());
        movie.setRating(updateMovie.getRating());

        List<Genre> genres = movie.getGenres().stream()
                .map(genre -> genreRepository.findByName(genre.getName()).isEmpty() ? genreRepository.save(genre): genreRepository.findByName(genre.getName()).get())
                .collect(Collectors.toList());

        movie.setGenres(genres);

        return movieRepository.save(movie);
    }

    public void deleteMovie(User user, Long id){
        Movie movie = getMovieById(id);
        ToWatchMovie toWatchMovie = toWatchMovieRepository.findByUserAndMovie(user, movie)
                .orElseThrow(() -> new RuntimeException("Movie not found in To Watch List"));
        toWatchMovieRepository.delete(toWatchMovie);
        movieRepository.deleteById(id);
    }

    @Transactional
    public void moveToWatched(User user, Long movieId){
        Movie movie = getMovieById(movieId);
        ToWatchMovie toWatchMovie = toWatchMovieRepository.findByUserAndMovie(user, movie)
                .orElseThrow(() -> new RuntimeException("Movie not found in To Watch List"));
        WatchedMovie watchedMovie = new WatchedMovie(user, toWatchMovie.getMovie());
        watchedMovieRepository.save(watchedMovie);
        toWatchMovieRepository.delete(toWatchMovie);

    }
}
