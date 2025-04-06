package com.aks.interviewready.api;

import com.aks.interviewready.boards.TicTacToeBoard;
import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Move;
import com.aks.interviewready.game.Player;

public class AIEngine {
    private static final int MIN_MOVES = 3;

    public Move suggestMove(Player player, Board board) {
        Move suggestion;
        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            // if moves < MIN_MOVES, AI cannot determine who will win, so start with basic moves
            if(countMoves(ticTacToeBoard) < MIN_MOVES) {
                suggestion = getBasicMove(player, ticTacToeBoard);
            } else {
                suggestion = getSmartMove(player, ticTacToeBoard);
            }
        } else {
            throw new IllegalArgumentException("No a valid board type");
        }

        return suggestion;
    }

    private Move getSmartMove(Player player, TicTacToeBoard ticTacToeBoard) {

        RuleEngine ruleEngine = new RuleEngine();

        // check for victorious move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(new Cell(i, j)) == null) {
                    Board copy = ticTacToeBoard.copy();
                    Move move = new Move(new Cell(i, j), player);
                    copy.move(move);
                    // if the player makes this move
                    // either player will win or the game will end (board full)
                    if(ruleEngine.isCompleted(copy).isOver()) {
                        return move;
                    }
                }
            }
        }

        // since the player cannot win, block the opponents move
        Player opponentPlayer = player.flip();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(new Cell(i, j)) == null) {
                    Board copy = ticTacToeBoard.copy();
                    Move move = new Move(new Cell(i, j), opponentPlayer);
                    copy.move(move);
                    // opponent will win with this move, so block it
                    if(ruleEngine.isCompleted(copy).isOver()) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }

        return null;
    }

    private int countMoves(TicTacToeBoard ticTacToeBoard) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(new Cell(i, j)) != null) {
                    count++;
                }
            }
        }
        return count;
    }

    private Move getBasicMove(Player player, TicTacToeBoard ticTacToeBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(new Cell(i, j)) == null) {
                    return new Move(new Cell(i, j), player);
                }
            }
        }
        return null;
    }
}
