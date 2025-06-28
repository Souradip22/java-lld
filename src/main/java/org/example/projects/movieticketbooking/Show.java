package org.example.projects.movieticketbooking;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class Show {
    private String id;
    private Movie movie;
    private Theater theater;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<String, Seat> seats;

    public Show(Movie movie, Theater theater, LocalDateTime startTime, LocalDateTime endTime, Map<String, Seat> seats) {
        this.id = generateShowId();
        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = seats;
        this.theater.addShow(this);
    }

    private String generateShowId() {
        return "SHOW-" + UUID.randomUUID().toString().substring(0, 5);
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }

    public void setSeats(Map<String, Seat> seats) {
        this.seats = seats;
    }
}
