package org.example.designpatterns.structural.adapterpattern;

public class Main {
    public static void main(String[] args) {

        PaymentGateway paymentGateway = new PaymentGatewayImpl();
        paymentGateway.pay(String.valueOf(100));

        // new payment gateway stripe has been introduced, but client wants
        // to call the same pay method
        StripeAPI stripe = new StripeAPI();
        PaymentGateway newPaymentGateway = new StripeAdapter(stripe, "USD");
        newPaymentGateway.pay(String.valueOf(200));
    }
}
