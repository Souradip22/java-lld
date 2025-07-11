package org.example.designpatterns.structural.compositepattern;

public class Developer implements Employee{
    private String name;
    private String role;

    public Developer(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public void showDetails() {
        System.out.println("Developer: " + name + " Role: "+ role);
    }
}
