package com.aks.ducksimulator.interviewready.api;

import com.aks.ducksimulator.interviewready.boards.TicTacToeBoard;
import com.aks.ducksimulator.interviewready.game.Board;
import com.aks.ducksimulator.interviewready.game.Move;

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

