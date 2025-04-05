package interviewready;

import interviewready.boards.TicTacToeBoard;
import interviewready.game.Board;
import interviewready.game.GameResult;
import interviewready.game.Move;
import interviewready.game.Player;

public class GameEngine {
    public Board start() {
        return new Board();
    }

    public void move(Board board, Player player, Move move) {

    }

    public GameResult isCompleted(Board board) {

        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            String firstCharacter = "-";

            boolean isRowComplete = true;
            for (int i = 0; i < 3; i++) {
                isRowComplete = true;
                firstCharacter = ticTacToeBoard.getCell(i, 0);
                for (int j = 0; j < 3; j++) {
                    if (!firstCharacter.equals(ticTacToeBoard.getCell(i, j))) {
                        isRowComplete = false;
                        break;
                    }
                }
                if (isRowComplete)
                    break;
            }

            if (isRowComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean isColComplete = true;
            for (int i = 0; i < 3; i++) {
                isColComplete = true;
                firstCharacter = ticTacToeBoard.getCell(0, i);
                for (int j = 0; j < 3; j++) {
                    if (!firstCharacter.equals(ticTacToeBoard.getCell(j, i))) {
                        isColComplete = false;
                        break;
                    }
                }
                if (isColComplete)
                    break;
            }

            if (isColComplete) {
                return new GameResult(true, firstCharacter);
            }


            boolean isDiagComplete = true;
            firstCharacter = ticTacToeBoard.getCell(0, 0);
            for (int i = 0; i < 3; i++) {
                if (!firstCharacter.equals(ticTacToeBoard.getCell(i, i))) {
                    isDiagComplete = false;
                    break;
                }
            }

            if (isDiagComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean isRevDiagComplete = true;
            firstCharacter = ticTacToeBoard.getCell(2,0);
            for (int i = 0; i < 3; i++) {
                if (!firstCharacter.equals(ticTacToeBoard.getCell(2 - i, i))) {
                    isRevDiagComplete = false;
                    break;
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
                return new GameResult(false, "-");
            } else {
                return new GameResult(false, "-");
            }
        } else {
            return new GameResult(false, "-");
        }
    }

}

