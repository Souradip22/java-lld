package org.example.projects.onlinefooddelivery;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String id;
    private String name;
    private String phone;
    private List<Order> orders;

    public User(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }
    public boolean removeOrder(Order order){
        return this.orders.remove(order);
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
