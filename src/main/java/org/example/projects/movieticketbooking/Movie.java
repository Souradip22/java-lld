package org.example.projects.movieticketbooking;

import java.util.UUID;

public class Movie {
//    private String id;
    private String name;
    private String description;
    private String language;
    private double duration;

    public Movie(String name, String description, String language, double duration) {
//        this.id = generateMovieId();
        this.name = name;
        this.description = description;
        this.language = language;
        this.duration = duration;
    }

    private String generateMovieId() {
        return "MOV-" + UUID.randomUUID().toString().substring(0,4);
    }

//    public String getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public double getDuration() {
        return duration;
    }
}
