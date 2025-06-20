package org.example.projects.snakeladdergame;

import java.util.*;

/**
 * Snake and Ladder Board class which manages game flow, players, snakes, and ladders.
 */
public class Board {
    private final int size;
    private final List<Player> players;
    private int currentPlayerIndex;
    private final Map<Integer, Integer> snakesMap;
    private final Map<Integer, Integer> laddersMap;
    private Player currentPlayer;

    public Board(int size) {
        this.size = size;
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.snakesMap = new HashMap<>();
        this.laddersMap = new HashMap<>();
    }

    // Add a player to the game
    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    // Add a snake to the board
    public void addSnake(int head, int tail) {
        if (head <= tail) {
            throw new IllegalArgumentException("Snake's head must be greater than its tail.");
        }
        if (isInvalidPosition(head) || isInvalidPosition(tail) || laddersMap.containsKey(head) || laddersMap.containsValue(head)) {
            throw new IllegalArgumentException("Invalid snake position.");
        }
        if (snakesMap.containsKey(head)) {
            throw new IllegalArgumentException("Snake head already exists.");
        }
        snakesMap.put(head, tail);
    }

    // Add a ladder to the board
    public void addLadder(int start, int end) {
        if (end <= start) {
            throw new IllegalArgumentException("Ladder's end must be greater than its start.");
        }
        if (isInvalidPosition(start) || isInvalidPosition(end) || snakesMap.containsKey(start) || snakesMap.containsValue(start)) {
            throw new IllegalArgumentException("Invalid ladder position.");
        }
        if (laddersMap.containsKey(start)) {
            throw new IllegalArgumentException("Ladder start already exists.");
        }
        laddersMap.put(start, end);
    }

    // Validate board positions
    private boolean isInvalidPosition(int pos) {
        return pos <= 0 || pos >= size;
    }

    // Get final position after hitting a snake or climbing a ladder
    private int getFinalPosition(int pos) {
        if (snakesMap.containsKey(pos)) {
            System.out.println("---- STEPPED ON A SNAKE! Moving down to " + snakesMap.get(pos));
            return snakesMap.get(pos);
        }
        if (laddersMap.containsKey(pos)) {
            System.out.println("---- FOUND A LADDER! Climbing up to " + laddersMap.get(pos));
            return laddersMap.get(pos);
        }
        return pos;
    }

    // Show player move details
    private void showDetails(int diceNo, int finalPos) {
        System.out.println("Player: " + currentPlayer.getName() + " | Current Position: " + currentPlayer.getCurrentPos() +
                " | Rolled: " + diceNo + " | Moving to: " + finalPos);
    }

    // Main game loop
    public void play() {
        System.out.println("===== Snake and Ladder Game Started =====");

        while (true) {
            currentPlayer = players.get(currentPlayerIndex);
            int dice = Dice.roll();
            int nextPos = currentPlayer.getCurrentPos() + dice;

            if (nextPos > size) {
                System.out.println("Player: " + currentPlayer.getName() +
                        " rolled a " + dice + " but move exceeds board size. Staying at same position.");
            } else {
                int finalPos = getFinalPosition(nextPos);
                showDetails(dice, finalPos);

                if (finalPos == size) {
                    System.out.println("\n🎉🎉 " + currentPlayer.getName() + " has WON the GAME! 🎉🎉");
                    break;
                }
                currentPlayer.setCurrentPos(finalPos);
            }

            // Move to next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

            // Optional: Add delay for better readability
            // Thread.sleep(1000);
        }
        System.out.println("===== Game Over =====");
    }
}
