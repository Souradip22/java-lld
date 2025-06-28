package org.example.projects.movieticketbooking;

import java.util.List;
import java.util.UUID;

public class Theater {
    private String id;
    private String name;
    private String location;
    private List<Show> shows;

    public Theater(String name, String location, List<Show> shows) {
        this.id = generateTheaterId();
        this.name = name;
        this.location = location;
        this.shows = shows;
    }

    private String generateTheaterId() {
        return "THEATER-" + UUID.randomUUID().toString().substring(0, 4);
    }

    public void addShow(Show show){
        this.shows.add(show);
    }
    public void removeShow(Show show){
        this.shows.remove(show);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
