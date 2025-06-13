package org.example.projects.taskmanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String email;
    List<Task> tasks;

    public User(String name, String email) {
        this.id = generateUserId();
        this.name = name;
        this.email = email;
        this.tasks = new ArrayList<>();
    }

    private String generateUserId() {
        return "USER-" + UUID.randomUUID().toString().substring(0,4);
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

    public List<Task> getTasks() {
        return tasks;
    }
}
