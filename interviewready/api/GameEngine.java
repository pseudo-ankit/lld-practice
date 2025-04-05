package interviewready.api;

import interviewready.boards.TicTacToeBoard;
import interviewready.game.Board;
import interviewready.game.Move;

public class GameEngine {
    public Board start(String type) {
        if ("TicTacToe".equals(type)) {
            return new TicTacToeBoard();
        } else {
            throw new IllegalArgumentException("No a valid board type");
        }
    }

    public void move(Board board, Move move) {
        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            ticTacToeBoard.move(move);
        } else {
            throw new IllegalArgumentException("No a valid board type");
        }
    }
}

