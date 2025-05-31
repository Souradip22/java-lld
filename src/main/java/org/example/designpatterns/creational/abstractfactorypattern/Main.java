package org.example.designpatterns.creational.abstractfactorypattern;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter preferred OS(MAC/Windows): ");
        String type = sc.nextLine();
        GUIFactory factory;

        if (type.equalsIgnoreCase("mac")){
            factory = new MacFactory();
        } else {
            factory = new WindowsFactory();
        }

        Application app = new Application(factory);
        app.renderUI();
    }
}
