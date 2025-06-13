package org.example.projects.onlinefooddelivery;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {
    private String id;
    private String name;
    private List<MenuItem> menuItems;
    private List<Order> orders;

    public Restaurant(String name, List<MenuItem> menuItems) {
        this.id = generateRestaurantId();
        this.name = name;
        this.menuItems = menuItems;
        this.orders = new ArrayList<>();
    }

    private String generateRestaurantId() {
        return "RES-" + UUID.randomUUID().toString().substring(0,6);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }
}
