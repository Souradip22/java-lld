package org.example.projects.splitwise;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Expense {
    private String id;
    private String name;
    private Group group;
    private User paidBy;
    private double amount;
    private List<Split> splits;
    private LocalDateTime createdAt;

    public Expense(String name, Group group, User paidBy,
                   double amount, List<Split> splits) {
        this.id = "ex-"+UUID.randomUUID().toString().substring(0, 5);
        this.name = name;
        this.group = group;
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
        this.createdAt = LocalDateTime.now();
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void showDetails() {
        System.out.println(
                "\n--- Expense Details ---" +
                "\nID         : " + id +
                "\nName       : " + name +
                "\nGroup      : " + group.getName() +
                "\nPaid By    : " + paidBy.getName() +
                "\nAmount     : " + amount +
//                "\nSplits     : " + splits +
//                "\nCreated At : " + createdAt +
                "\n------------------------\n"
        );

    }

}
