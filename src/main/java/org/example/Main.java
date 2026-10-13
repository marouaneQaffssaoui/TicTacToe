package org.example;

import org.example.entities.Game;
import org.example.entities.Player;
import org.example.enums.Symbol;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Player marouane = new Player("Marouane", Symbol.X);
        Player saad = new Player("Saad", Symbol.O);
        Game game = new Game(marouane, saad, 3);
        System.out.println("=================================");
        System.out.println("========== TIC TAC TOE ==========");
        System.out.println("=================================");

        game.makeMove(0, 2);
        game.makeMove(0, 0);
        game.makeMove(1, 1);
        game.makeMove(1, 0);
        game.makeMove(2, 0);

        game.printBoard();

        System.out.println("Result: " + game.getStatus());
        Player winner = game.getTheWinnerPlayer();
        if (winner != null) {
            System.out.println("Winner: " + winner.getName());
        }
    }
}