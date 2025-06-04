package org.example.designpatterns.creational.builderpattern;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MealTest {

    @Test
    void testMealWithRequiredFieldsOnly() {
        Meal meal = new Meal.MealBuilder("Pizza", "Fries", "Cola").build();

        assertNotNull(meal);
        // You could use reflection or add getters for field value verification
    }

    @Test
    void testMealWithAllFields() {
        Meal meal = new Meal.MealBuilder("Burger", "Salad", "Juice")
                .setDessert("Ice Cream")
                .setAppetizer("Nachos")
                .build();

        assertNotNull(meal);
        // You may add public getters for field assertions if needed
    }

    @Test
    void testPrintMealSummary() {
        Meal meal = new Meal.MealBuilder("Sushi", "Miso Soup", "Green Tea")
                .setDessert("Mochi")
                .setAppetizer("Edamame")
                .build();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        meal.printMealSummary();

        String output = outContent.toString();
        assertTrue(output.contains("Sushi"));
        assertTrue(output.contains("Miso Soup"));
        assertTrue(output.contains("Green Tea"));
        assertTrue(output.contains("Mochi"));
        assertTrue(output.contains("Edamame"));
    }
}
