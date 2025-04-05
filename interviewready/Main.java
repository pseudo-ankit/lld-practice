package interviewready;

import interviewready.api.AIEngine;
import interviewready.api.GameEngine;
import interviewready.api.RuleEngine;
import interviewready.game.Board;
import interviewready.game.Cell;
import interviewready.game.Move;
import interviewready.game.Player;

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
