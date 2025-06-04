package org.example.designpatterns.structural.adapterpattern;

public class PaymentGatewayImpl implements PaymentGateway{
    @Override
    public void pay(String amount) {
        System.out.println("Processing payment of amount :" + amount);
    }
}
