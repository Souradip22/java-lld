package org.example.designpatterns.behavioral.observerpattern.publisher;

import org.example.designpatterns.behavioral.observerpattern.subscriber.StockAlertSubscriber;

import java.util.ArrayList;
import java.util.List;

public class LaptopStockAlertPublisherImpl implements StockAlertPublisher{
    List<StockAlertSubscriber> allSubscribers = new ArrayList<>();
    static int stockCount = 0;
    @Override
    public void add(StockAlertSubscriber subscriber) {
        allSubscribers.add(subscriber);
    }
    @Override
    public void remove(StockAlertSubscriber subscriber) {
        allSubscribers.remove(subscriber);
    }
    @Override
    public void notifySubscribers() {
        for(StockAlertSubscriber subscriber : allSubscribers) {
            subscriber.update();
        }
    }
    @Override
    public int getStockCount() {
        return stockCount;
    }
    @Override
    public void setStockCount(int newCount) {
        int prevCount = stockCount;
        stockCount = newCount;
        if (prevCount == 0) {
            notifySubscribers();
        }


    }
}
