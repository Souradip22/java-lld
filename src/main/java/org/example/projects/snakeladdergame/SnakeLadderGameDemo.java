package org.example.projects.snakeladdergame;

public class SnakeLadderGameDemo {
    public static void main(String[] args) {
        Board board = new Board(100);

        board.addPlayer("Ajay");
        board.addPlayer("Rahul");
        board.addPlayer("Jojo");
        board.addPlayer("Sunil");

        board.addSnake(17, 7);
        board.addSnake(40, 10);
        board.addSnake(80, 23);
        board.addSnake(94, 55);

        board.addLadder(5 ,20);
        board.addLadder(16 ,36);
        board.addLadder(42 ,73);
        board.addLadder(56 ,96);

        board.play();
    }
}
