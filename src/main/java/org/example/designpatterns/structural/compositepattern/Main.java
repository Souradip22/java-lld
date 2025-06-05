package org.example.designpatterns.structural.compositepattern;

public class Main {
    public static void main(String[] args) {
        Employee developer1 = new Developer("John", "Frontend");
        Employee developer2 = new Developer("Alice", "Backend");

        Employee designer1 = new Designer("Bob", "UX");
        Employee designer2 = new Designer("Ozan", "UI");

        Manager manager1 = new Manager("Heather");
        Manager manager2 = new Manager("Sky");

        manager1.addSubOrdinates(developer1);
        manager1.addSubOrdinates(designer1);

        manager2.addSubOrdinates(manager1);
        manager2.addSubOrdinates(designer2);
        manager2.addSubOrdinates(developer2);


        manager1.showDetails();
        System.out.println("----");
        manager2.showDetails();


    }
}
