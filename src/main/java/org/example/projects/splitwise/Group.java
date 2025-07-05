package org.example.projects.splitwise;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {
    private String id;
    private String name;
    private String description;
    private List<User> users;
    List<Expense> expenses;
    private LocalDateTime createdAt;

    public Group(String name, String description, List<User> users) {
        this.id = "gr-" + UUID.randomUUID().toString().substring(0,5);
        this.name = name;
        this.description = description;
        this.users = users;
        this.expenses = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public void addUser(User user){
        this.users.add(user);
    }
    public void addExpense(Expense ex){
        this.expenses.add(ex);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
