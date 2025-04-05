package interviewready;

import interviewready.game.Board;
import interviewready.game.Cell;
import interviewready.game.Move;
import interviewready.game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        Board ticTacToe = engine.start("TicTacToe");

        // making moves
        Player user = new Player("O");
        Player computer = new Player("X");
        int row, col;
        Scanner scanner = new Scanner(System.in);
        while(!engine.isCompleted(ticTacToe).isCompleted()) {
            System.out.println("Make your move :");
            row = scanner.nextInt();
            col = scanner.nextInt();
            engine.move(ticTacToe, user, new Move(new Cell(row, col)));

            if(!engine.isCompleted(ticTacToe).isCompleted()) {
                Move move = engine.suggestMove(computer, ticTacToe);
                engine.move(ticTacToe, computer, move);
            }
        }

        System.out.println("Game Result : " + engine.isCompleted(ticTacToe));
    }
}
