package org.example.designpatterns.projects.loggerapp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        Scanner sc = new Scanner(System.in);

        // Get an info message from the user
        System.out.print("Enter an info message: ");
        String infoMessage = sc.nextLine();

        // TODO: Log the info message using the appropriate logging method.
        logger.info(infoMessage);


        // Get a warning message from the user
        System.out.print("Enter a warning message: ");
        String warnMessage = sc.nextLine();

        // TODO: Log the warn message using the appropriate logging method.
        logger.warn(warnMessage);


        // Get an error message from the user
        System.out.print("Enter an error message: ");
        String errorMessage = sc.nextLine();

        // TODO: Log the error message using the appropriate logging method.
        logger.error(errorMessage);



        sc.close();
    }
}
