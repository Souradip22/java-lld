package org.example.projects.onlinefooddelivery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class FoodDeliveryService {
    private Map<String, Order> orderMap;
    private Map<String, Restaurant> restaurantMap;
    private Map<String, Customer> customerMap;
    private Map<String, DeliveryAgent> deliveryAgentMap;
    private PaymentProcessor paymentProcessor;

    public FoodDeliveryService() {
        this.orderMap = new ConcurrentHashMap<>();
        this.restaurantMap = new ConcurrentHashMap<>();
        this.customerMap = new ConcurrentHashMap<>();
        this.deliveryAgentMap = new ConcurrentHashMap<>();
        this.paymentProcessor = new DebitCardPayment();
    }

    public Restaurant addRestaurant(String name, List<MenuItem> menuItems){
        Restaurant restaurant = new Restaurant(name, menuItems);
        restaurantMap.put(restaurant.getId(), restaurant);
        return restaurant;
    }
    public Customer addCustomer(String name, String phone){
        Customer customer = new Customer(name, phone);
        customerMap.put(customer.getId(), customer);
        return customer;
    }
    public DeliveryAgent addDeliveryAgent(String name, String phone){
        DeliveryAgent deliveryAgent = new DeliveryAgent(name, phone);
        deliveryAgentMap.put(deliveryAgent.getId(), deliveryAgent);
        return deliveryAgent;
    }

    public List<MenuItem> getMenuItems(String id){
        if (!restaurantMap.containsKey(id)) throw new IllegalStateException(
                "Restaurant is not in DB");
        return restaurantMap.get(id).getMenuItems();
    }

    public Order placeOrder(Restaurant restaurant,
                           List<OrderItem> orderItemList, Customer customer){
        Order order = new Order(restaurant, orderItemList, customer);

        if (paymentProcessor.processPayment(order.getTotalAmount())){
            orderMap.put(order.getId(), order);
            notifyCustomer(" your order has been placed and your order ID: " + order.getId());
            order.getCustomer().addOrder(order);
            order.getRestaurant().addOrder(order);
        }
        return order;

    }
    private void notifyCustomer(String msg){
        System.out.println("Customer notification: "+msg);
    }
    private void notifyDeliveryAgent(String msg){
        System.out.println("Delivery agent notification: "+msg);
    }
    private void notifyRestaurant(String msg){
        System.out.println("Restaurant notification: "+ msg);
    }

    public DeliveryAgent assignDeliveryAgent(Order order) {
        Optional<DeliveryAgent> agent =
                deliveryAgentMap.values().stream().filter((DeliveryAgent deliveryAgent)-> !deliveryAgent.isAvailable()).findFirst();
        if (agent.isPresent()){

            order.setDeliveryAgent(agent.get());
            agent.get().setAvailable(false);
            order.setOrderState(OrderStatus.READY);
            notifyCustomer("Delivery partner assigned - " + agent.get().getName());
        } else {
            System.out.println("No Delivery partners are available");
        }

        return agent.get();
    }

    public void prepareOrder(Restaurant restaurant1, Order order1) {
        if (restaurant1.getOrders().contains(order1)){
            if (order1.getOrderState() == OrderStatus.PENDING){
                order1.setOrderState(OrderStatus.PREPARING);
                notifyCustomer(restaurant1.getName() + " started preparing " +
                        "your order!");
            } else{
                System.out.println("Order is already prepared, and current " +
                        "status is "+ order1.getOrderState());
            }
        } else {
            System.out.println("Order: "+ order1.getId() +" is not placed to " +
                    "the " +
                    "restaurant " + restaurant1.getName());
        }
    }

    public void cancelOrder(Order order) {
        if(order.getOrderState().equals(OrderStatus.PENDING)){
            notifyRestaurant("Order with id "+order.getId() +"has been cancelled ");
            order.setOrderState(OrderStatus.CANCELED);
            // refund user the amount
        } else {
            System.out.println("Unable to cancel, order not in pending state");
        }
    }
}
