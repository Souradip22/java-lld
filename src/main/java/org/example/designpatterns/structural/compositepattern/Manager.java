package org.example.designpatterns.structural.compositepattern;

import java.util.ArrayList;
import java.util.List;

public class Manager implements Employee{

    private String name;
    private List<Employee> subordinates = new ArrayList<>();

    public Manager(String name) {
        this.name = name;
    }

    public void addSubOrdinates(Employee empl){
        subordinates.add(empl);
    }

    @Override
    public void showDetails() {
        System.out.println("Manager: " + name);
        for(Employee employee: subordinates){
            System.out.print("Under " + name + " --> ");
            employee.showDetails();
        }
    }
}
