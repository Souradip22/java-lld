package org.example.projects.onlinefooddelivery;

import java.util.UUID;

public class DeliveryAgent extends User{

    private boolean isAvailable;
    public DeliveryAgent(String name, String phone) {
        super(generateDeliveryAgentId(), name, phone);
    }

    private static String generateDeliveryAgentId() {
        return "DEL-"+UUID.randomUUID().toString().substring(0,6);
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
