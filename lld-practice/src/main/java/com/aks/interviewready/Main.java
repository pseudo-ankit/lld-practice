package com.aks.interviewready;

import com.aks.interviewready.api.AIEngine;
import com.aks.interviewready.api.GameEngine;
import com.aks.interviewready.api.RuleEngine;
import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Move;
import com.aks.interviewready.game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        Board ticTacToe = gameEngine.start("TicTacToe");

        // making moves
        Player user = new Player("O");
        Player computer = new Player("X");
        int row, col;
        Scanner scanner = new Scanner(System.in);
        while (!ruleEngine.isCompleted(ticTacToe).isOver()) {
            System.out.println("Make your move :");
            row = scanner.nextInt();
            col = scanner.nextInt();
            gameEngine.move(ticTacToe, new Move(new Cell(row, col), user));
            System.out.println(ticTacToe);

            if (!ruleEngine.isCompleted(ticTacToe).isOver()) {
                Move move = aiEngine.suggestMove(computer, ticTacToe);
                gameEngine.move(ticTacToe, move);
                System.out.println(ticTacToe);
            }
        }

        System.out.println("Game Result : " + ruleEngine.isCompleted(ticTacToe));
        System.out.println(ticTacToe);
    }
}
