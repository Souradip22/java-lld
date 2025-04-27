package org.example.designpatterns.behavioral.strategypattern;

public class StrategyTest {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        paymentService.setPaymentStrategy(new DebitCardPayment());

        paymentService.pay();

        paymentService.setPaymentStrategy(new UPIPayment());
        paymentService.pay();
    }
}
