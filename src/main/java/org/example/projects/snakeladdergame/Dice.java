package org.example.projects.snakeladdergame;

import java.util.Random;

public class Dice {
    public static int roll() {
        Random random = new Random();
        return random.nextInt(6) + 1; // Generates number from 1 to 6
    }
}
