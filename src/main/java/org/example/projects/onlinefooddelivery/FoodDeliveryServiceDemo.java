package org.example.projects.onlinefooddelivery;

import java.util.List;

public class FoodDeliveryServiceDemo {
    public static void main(String[] args) {
        FoodDeliveryService deliveryService = new FoodDeliveryService();

        List<MenuItem> menuItems = List.of(
                new MenuItem("Burger", "Cheese burger", 200),
                new MenuItem("Pizza", "Paneer pizza", 500),
                new MenuItem("Mac & Cheese", "Mac and chese", 300)
        );
        Restaurant restaurant1 = deliveryService.addRestaurant("Restaurant 1",
                menuItems);

        DeliveryAgent deliveryAgent1 = deliveryService.addDeliveryAgent("Praveen",
                "9876598765");
        DeliveryAgent deliveryAgent2 = deliveryService.addDeliveryAgent("Suresh",
                "9876598765");
        DeliveryAgent deliveryAgent3 = deliveryService.addDeliveryAgent("Sourav",
                "9876598765");

        Customer customer1 = deliveryService.addCustomer("Ravi", "567895678");
        Customer customer2 =  deliveryService.addCustomer("Deepak", "567895678");
        Customer customer3 =  deliveryService.addCustomer("Rupali", "567895678");

        List<MenuItem> menuItems1 =
                deliveryService.getMenuItems(restaurant1.getId());

//        List<OrderItem> orderItems1 = menuItems1.stream()
//                .map((MenuItem menuItem) -> new OrderItem(menuItem, 2))
//                .collect(Collectors.toList());

        List<OrderItem> orderItems2 = List.of(
                new OrderItem(menuItems1.get(0), 1),
                new OrderItem(menuItems1.get(2), 2)
        );

        Order order1 = deliveryService.placeOrder(restaurant1, orderItems2,
                customer1);
        order1.printDetails();
        deliveryService.prepareOrder(restaurant1, order1);
        System.out.println(order1.getOrderState());


        DeliveryAgent assignedDeliveryAgent =
                deliveryService.assignDeliveryAgent(order1);
        System.out.println(order1.getOrderState());
        System.out.println("SECOND ORDER -------");
        Order order2 = deliveryService.placeOrder(restaurant1, orderItems2,
                customer2);
        order2.printDetails();
        deliveryService.cancelOrder(order2);


        restaurant1.getOrders().forEach((Order order)-> System.out.println(
                restaurant1.getName() + " orders list : " + order.getId() +
                        " status: " + order.getOrderState()));

    }
}
