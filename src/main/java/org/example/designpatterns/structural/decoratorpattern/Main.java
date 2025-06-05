package org.example.designpatterns.structural.decoratorpattern;

public class Main {

    public static void main(String[] args) {
        Coffee simpleCoffee = new SimpleCoffee();

        CoffeeDecorator milkCoffee = new MilkDecorator(simpleCoffee);

        System.out.println(milkCoffee.getDescription());
        System.out.println(milkCoffee.price());


        CoffeeDecorator milkSugarCoffee = new SugarDecorator(milkCoffee);

        System.out.println(milkSugarCoffee.getDescription());
        System.out.println(milkSugarCoffee.price());

    }
}
