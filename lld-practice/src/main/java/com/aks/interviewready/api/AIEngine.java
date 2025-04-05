package com.aks.ducksimulator.interviewready.api;

import com.aks.ducksimulator.interviewready.boards.TicTacToeBoard;
import com.aks.ducksimulator.interviewready.game.Board;
import com.aks.ducksimulator.interviewready.game.Cell;
import com.aks.ducksimulator.interviewready.game.Move;
import com.aks.ducksimulator.interviewready.game.Player;

public class AIEngine {
    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToeBoard.getCell(i, j) == null) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
            throw new IllegalStateException("No cells are available !");
        } else {
            throw new IllegalArgumentException("No a valid board type");
        }
    }
}
