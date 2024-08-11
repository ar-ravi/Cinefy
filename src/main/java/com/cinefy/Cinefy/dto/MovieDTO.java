package com.cinefy.Cinefy.dto;

import java.util.List;

public class MovieDTO {
    private String title;
    private String genres;
    private Integer year;
    private Double rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "title='" + title + '\'' +
                ", genres='" + genres + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                '}';
    }
}
