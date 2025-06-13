package org.example.projects.onlinefooddelivery;

import java.util.List;
import java.util.UUID;

public class Order {
    private String id;
    private Restaurant restaurant;
    private List<OrderItem> orderItemList;
    private Customer customer;
    private DeliveryAgent deliveryAgent;
    private double totalAmount;
    private OrderStatus orderState;

    public Order(Restaurant restaurant, List<OrderItem> orderItemList, Customer customer) {
        this.id = generateOrderId();
        this.restaurant = restaurant;
        this.orderItemList = orderItemList;
        this.customer = customer;
        this.deliveryAgent = null;
        this.totalAmount = calculateTotalAmount();
        this.orderState = OrderStatus.PENDING;
    }

    private double calculateTotalAmount() {
        return orderItemList.stream()
                .mapToDouble((OrderItem orderItem) ->
                        orderItem.getMenuItem().getPrice() * orderItem.getQuantity())
                .sum();
    }

    private String generateOrderId() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 6);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public DeliveryAgent getDeliveryAgent() {
        return deliveryAgent;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderStatus orderState) {
        this.orderState = orderState;
    }

    public String getId() {
        return id;
    }

    public void setDeliveryAgent(DeliveryAgent deliveryAgent) {
        this.deliveryAgent = deliveryAgent;
    }

    public void printDetails() {
        System.out.println("Order Details: \n" +
                "id='" + id + '\'' +
                "\nRestaurant=" + restaurant.getName() +
                getOrderItem() +
                "\nTotal amount" + totalAmount +
                "\nOrder status :" + orderState );
    }

    public String getOrderItem(){
        StringBuilder finalOp = new StringBuilder();
        finalOp.append("\nItems:\n");
        for (OrderItem orderItem: orderItemList){
            finalOp.append(orderItem.getMenuItem().getName()).append(" x ").append(orderItem.getQuantity()).append(" \n");
        }
        return finalOp.toString();
    }

}
