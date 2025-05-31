package org.example.designpatterns.creational.builderpattern;

public class Main {
    public static void main(String[] args) {

        String fullMainDish = "Grilled Chicken Breast";
        String fullSideDish = "Garlic Mashed Potatoes";
        String fullDrink = "Iced Tea";
        String fullDessert = "Chocolate Lava Cake";
        String fullAppetizer = "Caesar Salad";

        Meal meal =
                new Meal.MealBuilder(fullMainDish, fullSideDish, fullDrink)
                        .setDessert(fullDessert)
                        .setAppetizer(fullAppetizer)
                        .build();

        System.out.println("Full Meal Summary:");
        meal.printMealSummary();

        Meal halfMeal =
                new Meal.MealBuilder("Turkey Sandwich", "Potato Chips", "Lemonade")
                        .build();

        System.out.println("Half Meal Summary:");
        halfMeal.printMealSummary();

    }
}
