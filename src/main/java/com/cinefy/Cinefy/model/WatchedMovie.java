package com.cinefy.Cinefy.model;

import jakarta.persistence.*;

@Entity
public class WatchedMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Movie movie;

    private Integer rating;

    public WatchedMovie() {}

    public WatchedMovie(Long id, User user, Movie movie, Integer rating) {
        this.id = id;
        this.user = user;
        this.movie = movie;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "WatchedMovie{" +
                "id=" + id +
                ", user=" + user +
                ", movie=" + movie +
                ", rating=" + rating +
                '}';
    }
}
