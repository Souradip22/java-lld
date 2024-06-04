package org.example.designpatterns.behavioral.observerpattern;

import org.example.designpatterns.behavioral.observerpattern.publisher.LaptopStockAlertPublisherImpl;
import org.example.designpatterns.behavioral.observerpattern.publisher.StockAlertPublisher;
import org.example.designpatterns.behavioral.observerpattern.subscriber.EmailStockAlertSubscriberImpl;
import org.example.designpatterns.behavioral.observerpattern.subscriber.PhoneStockAlertSubscriberImpl;
import org.example.designpatterns.behavioral.observerpattern.subscriber.StockAlertSubscriber;

public class Main {
    public static void main(String[] args) {
        StockAlertPublisher laptopStockPublisher = new LaptopStockAlertPublisherImpl();

        StockAlertSubscriber observer1 = new EmailStockAlertSubscriberImpl( laptopStockPublisher, "John@gmail.com");
        StockAlertSubscriber observer2 = new EmailStockAlertSubscriberImpl( laptopStockPublisher, "Tom@gmail.com");
        StockAlertSubscriber observer3 = new PhoneStockAlertSubscriberImpl( laptopStockPublisher, "987654321");

        laptopStockPublisher.add(observer1);
        laptopStockPublisher.add(observer2);
        laptopStockPublisher.add(observer3);

        laptopStockPublisher.setStockCount(10);
//        laptopStockPublisher.setStockCount(0);
//        laptopStockPublisher.setStockCount(20);
    }
}
