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
        System.out.println(ticTacToe);
        while (!ruleEngine.getState(ticTacToe).isOver()) {
            System.out.println("Make your move :");
            row = scanner.nextInt();
            col = scanner.nextInt();
            gameEngine.move(ticTacToe, new Move(new Cell(row, col), user));
            System.out.println(ticTacToe);

            if (!ruleEngine.getState(ticTacToe).isOver()) {
                Move move = new Move(aiEngine.suggestCellToPlay(computer, ticTacToe), computer);
                gameEngine.move(ticTacToe, move);
                System.out.println(ticTacToe);
            }
        }

        System.out.println("Game Result : " + ruleEngine.getState(ticTacToe));
        System.out.println(ticTacToe);
    }
}
