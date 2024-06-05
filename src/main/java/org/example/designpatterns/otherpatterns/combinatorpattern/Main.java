package org.example.designpatterns.otherpatterns.combinatorpattern;

import java.time.LocalDate;

import static org.example.designpatterns.otherpatterns.combinatorpattern.CustomerRegistrationValidator.*;

record Customer(String name, String email, String phoneNumber, LocalDate dob) {
}
public class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer(
                "John",
                "john@gmail.com",
                "091222",
                LocalDate.of(2018, 11, 22)
        );
        Customer customer2 = new Customer(
                "Anna",
                "anna@gmail.com",
                "091234",
                LocalDate.of(2002, 11, 22)
        );

        //Using Combinator pattern
        ValidationResult result = isEmailValid()
                .and(isAdult())
                .and(isPhoneNumberValid())
                .apply(customer1);

        System.out.println(result);
        if (result != ValidationResult.SUCCESS){
            throw new IllegalStateException(result.name());
        }
        //if valid we can store customer in DB

    }
}
