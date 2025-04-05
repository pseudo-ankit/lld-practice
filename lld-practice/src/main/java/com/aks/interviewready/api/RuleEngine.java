package com.aks.ducksimulator.interviewready.api;

import com.aks.ducksimulator.interviewready.boards.TicTacToeBoard;
import com.aks.ducksimulator.interviewready.game.Board;
import com.aks.ducksimulator.interviewready.game.GameResult;

public class RuleEngine {
    public GameResult isCompleted(Board board) {

        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            String firstCharacter = "-";

            boolean isRowComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = ticTacToeBoard.getCell(i, 0);
                isRowComplete = firstCharacter != null;
                if (firstCharacter != null) {
                    for (int j = 0; j < 3; j++) {
                        if (!firstCharacter.equals(ticTacToeBoard.getCell(i, j))) {
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
                firstCharacter = ticTacToeBoard.getCell(0, i);
                isColComplete = firstCharacter != null;
                if (firstCharacter != null) {
                    for (int j = 0; j < 3; j++) {
                        if (!firstCharacter.equals(ticTacToeBoard.getCell(j, i))) {
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


            firstCharacter = ticTacToeBoard.getCell(0, 0);
            boolean isDiagComplete = firstCharacter != null;
            if (firstCharacter != null) {
                for (int i = 0; i < 3; i++) {
                    if (!firstCharacter.equals(ticTacToeBoard.getCell(i, i))) {
                        isDiagComplete = false;
                        break;
                    }
                }
            }

            if (isDiagComplete) {
                return new GameResult(true, firstCharacter);
            }

            firstCharacter = ticTacToeBoard.getCell(2, 0);
            boolean isRevDiagComplete = firstCharacter != null;
            if (firstCharacter != null) {
                for (int i = 0; i < 3; i++) {
                    if (!firstCharacter.equals(ticTacToeBoard.getCell(2 - i, i))) {
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
                    if (ticTacToeBoard.getCell(i, j) != null) {
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
