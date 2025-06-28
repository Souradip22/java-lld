package org.example.projects.movieticketbooking;

import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String email;

    public User(String name, String email) {
        this.id = generateUserId();
        this.name = name;
        this.email = email;
    }
    private String generateUserId() {
        return "USER-" + UUID.randomUUID().toString().substring(0, 6);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
