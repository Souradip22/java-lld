package org.example.designpatterns.behavioral.strategypattern;

public class CreditCardPayment implements PaymentStrategy{
    @Override
    public void processPayment() {
        System.out.println("Processed Payment via credit card");
    }
}
