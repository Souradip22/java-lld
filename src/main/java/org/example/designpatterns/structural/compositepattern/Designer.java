package org.example.designpatterns.structural.compositepattern;

public class Designer implements Employee{
    private String name;
    private String role;

    public Designer(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public void showDetails() {
        System.out.println("Designer: " + name + " Role: "+ role);
    }
}
