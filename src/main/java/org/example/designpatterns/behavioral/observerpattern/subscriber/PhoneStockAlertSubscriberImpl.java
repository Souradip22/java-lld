package org.example.designpatterns.behavioral.observerpattern.subscriber;

import org.example.designpatterns.behavioral.observerpattern.publisher.StockAlertPublisher;

public class PhoneStockAlertSubscriberImpl implements StockAlertSubscriber{

    StockAlertPublisher publisher;
    String phoneNo;

    public PhoneStockAlertSubscriberImpl(StockAlertPublisher publisher, String phone) {
        this.publisher = publisher;
        this.phoneNo = phone;
    }

    @Override
    public void update() {
        sendSMS(phoneNo, "Item is available now, new Stock is -> " + publisher.getStockCount() );
    }
    public void sendSMS(String phone, String msg){
        //Actual Send SMS Logic can be here
        System.out.println("SMS sent to  :"  + phone + " --> " + msg);;
    }
}
