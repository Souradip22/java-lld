package org.example.designpatterns.structural.adapterpattern;

// You can't change this
public class StripeAPI {
    public void makePayment(String currency, String amount) {
        System.out.println("Stripe processing payment of amount " + amount +
                " " + currency);
    }
}