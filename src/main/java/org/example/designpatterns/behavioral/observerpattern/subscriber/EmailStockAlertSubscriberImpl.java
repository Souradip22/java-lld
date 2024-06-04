package org.example.designpatterns.behavioral.observerpattern.subscriber;

import org.example.designpatterns.behavioral.observerpattern.publisher.StockAlertPublisher;

public class EmailStockAlertSubscriberImpl implements StockAlertSubscriber{

    StockAlertPublisher publisher;
    String emailId;

    public EmailStockAlertSubscriberImpl(StockAlertPublisher publisher, String email) {
        this.publisher = publisher;
        this.emailId = email;
    }

    @Override
    public void update() {
        sendEmail(emailId, "Item is available now, new Stock is -> " + publisher.getStockCount());
    }
    public void sendEmail(String email, String msg){
        //Actual Send Email Logic can be here
        System.out.println("EMAIL send to  :"  + email + " --> " + msg);;
    }
}
