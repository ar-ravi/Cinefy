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

    public WatchedMovie() {}

    public WatchedMovie(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
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


    @Override
    public String toString() {
        return "WatchedMovie{" +
                "id=" + id +
                ", user=" + user +
                ", movie=" + movie +
                '}';
    }
}
