package org.example.projects.carrentalsystem;

public interface PaymentProcessor {
    boolean processPayment(double amount);
}

class CreditCardPaymentProcessor implements PaymentProcessor{
    @Override
    public boolean processPayment(double amount) {
        // actual payment API call
        return true;
    }
}
class DebitCardPaymentProcessor implements PaymentProcessor{
    @Override
    public boolean processPayment(double amount) {
        // actual payment API call
        return true;
    }
}
