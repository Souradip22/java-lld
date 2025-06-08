package org.example.projects.parkinglotsystem;

public interface PaymentProcessor {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentProcessor{
    @Override
    public boolean processPayment(double amount) {
        return true;
    }
}

class DebitCardPayment implements PaymentProcessor{
    @Override
    public boolean processPayment(double amount) {
        return true;
    }
}
