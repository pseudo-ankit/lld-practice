package com.aks.interviewready.api;

import com.aks.interviewready.boards.TicTacToeBoard;
import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.GameResult;

public class RuleEngine {
    public GameResult isCompleted(Board board) {

        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            String firstCharacter = "-";

            boolean isRowComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = ticTacToeBoard.getSymbol(new Cell(i, 0));
                isRowComplete = firstCharacter != null;
                if (firstCharacter != null) {
                    for (int j = 0; j < 3; j++) {
                        if (!firstCharacter.equals(ticTacToeBoard.getSymbol(new Cell(i, j)))) {
                            isRowComplete = false;
                            break;
                        }
                    }
                    if (isRowComplete)
                        break;
                }
            }

            if (isRowComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean isColComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = ticTacToeBoard.getSymbol(new Cell(0, i));
                isColComplete = firstCharacter != null;
                if (firstCharacter != null) {
                    for (int j = 0; j < 3; j++) {
                        if (!firstCharacter.equals(ticTacToeBoard.getSymbol(new Cell(j, i)))) {
                            isColComplete = false;
                            break;
                        }
                    }
                    if (isColComplete)
                        break;
                }
            }

            if (isColComplete) {
                return new GameResult(true, firstCharacter);
            }


            firstCharacter = ticTacToeBoard.getSymbol(new Cell(0, 0));
            boolean isDiagComplete = firstCharacter != null;
            if (firstCharacter != null) {
                for (int i = 0; i < 3; i++) {
                    if (!firstCharacter.equals(ticTacToeBoard.getSymbol(new Cell(i, i)))) {
                        isDiagComplete = false;
                        break;
                    }
                }
            }

            if (isDiagComplete) {
                return new GameResult(true, firstCharacter);
            }

            firstCharacter = ticTacToeBoard.getSymbol(new Cell(2, 0));
            boolean isRevDiagComplete = firstCharacter != null;
            if (firstCharacter != null) {
                for (int i = 0; i < 3; i++) {
                    if (!firstCharacter.equals(ticTacToeBoard.getSymbol(new Cell(2 - i, i)))) {
                        isRevDiagComplete = false;
                        break;
                    }
                }
            }

            if (isRevDiagComplete) {
                return new GameResult(true, firstCharacter);
            }


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
            return new GameResult(false, "-");
        }
    }
}
