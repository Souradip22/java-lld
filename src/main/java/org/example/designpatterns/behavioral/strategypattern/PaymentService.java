package org.example.designpatterns.behavioral.strategypattern;

public class PaymentService {
    private PaymentStrategy paymentStrategy;
    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }
    public void pay(){
        paymentStrategy.processPayment();
    }
}
