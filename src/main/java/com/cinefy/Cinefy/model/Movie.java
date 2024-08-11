package com.cinefy.Cinefy.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(
            name="movie_genre",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id")
    )
    private List<Genre> genres;  // List of genres

    private Integer releaseYear;  // Change from Date to Integer for release year

    private Double rating;

    // Constructors, Getters, and Setters
    public Movie() {}

    public Movie(String title, List<Genre> genres, Integer releaseYear, Double rating) {
        this.title = title;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }


}
