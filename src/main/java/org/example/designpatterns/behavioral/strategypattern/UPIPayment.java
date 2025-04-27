package org.example.designpatterns.behavioral.strategypattern;

public class UPIPayment implements PaymentStrategy{
    @Override
    public void processPayment() {
        System.out.println("Processed payment vis UPI");
    }
}
