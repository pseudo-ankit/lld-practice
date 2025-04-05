package com.aks.ducksimulator.interviewready;

import com.aks.ducksimulator.interviewready.api.AIEngine;
import com.aks.ducksimulator.interviewready.api.GameEngine;
import com.aks.ducksimulator.interviewready.api.RuleEngine;
import com.aks.ducksimulator.interviewready.game.Board;
import com.aks.ducksimulator.interviewready.game.Cell;
import com.aks.ducksimulator.interviewready.game.Move;
import com.aks.ducksimulator.interviewready.game.Player;

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
        while(!ruleEngine.isCompleted(ticTacToe).isCompleted()) {
            System.out.println("Make your move :");
            row = scanner.nextInt();
            col = scanner.nextInt();
            gameEngine.move(ticTacToe, new Move(new Cell(row, col), user));
            System.out.println(ticTacToe);

            if(!ruleEngine.isCompleted(ticTacToe).isCompleted()) {
                Move move = aiEngine.suggestMove(computer, ticTacToe);
                gameEngine.move(ticTacToe, move);
                System.out.println(ticTacToe);
            }
        }

        System.out.println("Game Result : " + ruleEngine.isCompleted(ticTacToe));
        System.out.println(ticTacToe);
    }
}
