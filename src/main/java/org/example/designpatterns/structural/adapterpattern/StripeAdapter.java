package org.example.designpatterns.structural.adapterpattern;

public class StripeAdapter implements PaymentGateway{

    StripeAPI stripeAPI;
    String currency;

    public StripeAdapter(StripeAPI stripeAPI, String currency) {
        this.stripeAPI = stripeAPI;
        this.currency = currency;
    }

    @Override
    public void pay(String amount) {
        this.stripeAPI.makePayment(currency, amount);
    }
}
