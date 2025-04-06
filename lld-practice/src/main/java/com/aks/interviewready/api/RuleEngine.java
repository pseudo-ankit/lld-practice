package com.aks.interviewready.api;

import com.aks.interviewready.boards.TicTacToeBoard;
import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.GameResult;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    public GameResult isCompleted(Board board) {

        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            GameResult gameResult;
            BiFunction<Integer, Integer, String> getNextInRow = (i,  j) -> board.getSymbol(new Cell(i, j));
            BiFunction<Integer, Integer, String> getNextInCol = (i,  j) -> board.getSymbol(new Cell(j, i));
            Function<Integer, String> getNextInDiag = (i) -> board.getSymbol(new Cell(i, i));
            Function<Integer, String> getNextInRevDiag = (i) -> board.getSymbol(new Cell(2-i, i));

            // check victory along any row
            gameResult = isVictorious(getNextInRow);
            if (gameResult.isOver()) return gameResult;

            // check victory along any col
            gameResult = isVictorious(getNextInCol);
            if (gameResult.isOver()) return gameResult;

            // check victory along any diagonal
            gameResult = isVictorious(getNextInDiag);
            if (gameResult.isOver()) return gameResult;

            // check victory along any reverse diagonal
            gameResult = isVictorious(getNextInRevDiag);
            if (gameResult.isOver()) return gameResult;


            int countOfFilledgetCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToeBoard.getSymbol(new Cell(i, j)) != null) {
                        countOfFilledgetCells++;
                    }
                }
            }
            if (countOfFilledgetCells == 9) {
                return new GameResult(true, "-");
            } else {
                return new GameResult(false, "-");
            }
        } else {
            throw new IllegalArgumentException("Invalid Board Type");
        }
    }

    private GameResult isVictorious(Function<Integer, String> getNextSymbol) {
        String firstCharacter = getNextSymbol.apply(0);
        boolean isStreakPossible = true;
        GameResult gameResult = new GameResult(false, "-");

        for (int i = 0; i < 3; i++) {
            if (firstCharacter == null || !firstCharacter.equals(getNextSymbol.apply(i))) {
                isStreakPossible = false;
                break;
            }
        }
        if (isStreakPossible) {
            gameResult = new GameResult(true, firstCharacter);
        }
        return gameResult;
    }

    private GameResult isVictorious(BiFunction<Integer, Integer, String> getNextSymbol) {
        GameResult gameResult = new GameResult(false, "-");
        for (int i = 0; i < 3; i++) {
            String firstCharacter = getNextSymbol.apply(i, 0);
            boolean isStreakPossible = true;

            for (int j = 0; j < 3; j++) {
                if (firstCharacter == null || !firstCharacter.equals(getNextSymbol.apply(i, j))) {
                    isStreakPossible = false;
                    break;
                }
            }
            if (isStreakPossible)
                return new GameResult(true, firstCharacter);
        }
        return gameResult;
    }
}
