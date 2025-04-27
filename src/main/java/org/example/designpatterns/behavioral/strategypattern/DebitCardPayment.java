package org.example.designpatterns.behavioral.strategypattern;

public class DebitCardPayment implements PaymentStrategy{

    @Override
    public void processPayment() {
        System.out.println("Payment processed vis Debit card");
    }
}
