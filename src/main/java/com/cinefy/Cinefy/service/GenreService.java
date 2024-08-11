package com.cinefy.Cinefy.service;

import com.cinefy.Cinefy.dao.GenreRepository;
import com.cinefy.Cinefy.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getOrCreateGenres(List<String> genreNames){
        List<Genre> genres = new ArrayList<>();
        for(String name: genreNames){
            Genre genre = genreRepository.findByName(name).orElseGet(() -> genreRepository.save(new Genre(name)));
            genres.add(genre);
        }
        return genres;
    }
}
