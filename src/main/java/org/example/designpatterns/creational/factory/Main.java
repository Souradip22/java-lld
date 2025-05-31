package org.example.designpatterns.creational.factory;

public class Main {
    public static void main(String[] args) {
        Notification notification = NotificationFactory.createNotification(
                "sms");
        notification.send("Hello world");
    }
}
