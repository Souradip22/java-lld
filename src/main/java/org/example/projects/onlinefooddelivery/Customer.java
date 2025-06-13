package org.example.projects.onlinefooddelivery;

import java.util.UUID;

public class Customer extends User{
    public Customer(String name, String phone) {
        super(generateUserId(), name, phone);
    }

    private static String generateUserId() {
        return "CUS-"+UUID.randomUUID().toString().substring(0,6);
    }

}
