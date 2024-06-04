package org.example.designpatterns.behavioral.observerpattern.publisher;

import org.example.designpatterns.behavioral.observerpattern.subscriber.StockAlertSubscriber;

public interface StockAlertPublisher {
    public void add(StockAlertSubscriber subscriber);
    public void remove(StockAlertSubscriber subscriber);
    public void notifySubscribers();
    public int getStockCount();
    public void setStockCount(int newCount);
}
