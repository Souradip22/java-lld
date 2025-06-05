package org.example.designpatterns.structural.decoratorpattern;

public class SimpleCoffee implements Coffee{
    @Override
    public String getDescription() {
        return "Coffee";
    }

    @Override
    public double price() {
        return 5;
    }

}
