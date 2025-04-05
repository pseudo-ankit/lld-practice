package interviewready;

public class GameEntry {
    public Board start() {

    }

    public void move(Board board, Player player, Move move) {

    }

    public GameResult isCompleted(Board board) {

        if(board instanceof TicTacToeBoard ticTacToeBoard) {
            String firstCharacter = "-";

            boolean isRowComplete = true;
            for(int i=0; i<3; i++) {
                isRowComplete = true;
                firstCharacter = ticTacToeBoard.cells[i][0];
                for (int j=0; j<3; j++) {
                    if(!firstCharacter.equals(ticTacToeBoard.cells[i][j])) {
                        isRowComplete = false;
                        break;
                    }
                }
                if(isRowComplete)
                    break;
            }

            if(isRowComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean isColComplete = true;
            for(int i=0; i<3; i++) {
                isColComplete = true;
                firstCharacter = ticTacToeBoard.cells[0][i];
                for (int j=0; j<3; j++) {
                    if(!firstCharacter.equals(ticTacToeBoard.cells[j][i])) {
                        isColComplete = false;
                        break;
                    }
                }
                if(isColComplete)
                    break;
            }

            if(isColComplete) {
                return new GameResult(true, firstCharacter);
            }


            boolean isDiagComplete = true;
            firstCharacter = ticTacToeBoard.cells[0][0];
            for (int i=0; i<3; i++) {
                if(!firstCharacter.equals(ticTacToeBoard.cells[i][i])) {
                    isDiagComplete = false;
                    break;
                }
            }

            if(isDiagComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean isRevDiagComplete = true;
            firstCharacter = ticTacToeBoard.cells[2][0];
            for (int i=0; i<3; i++) {
                if(!firstCharacter.equals(ticTacToeBoard.cells[2-i][i])) {
                    isRevDiagComplete = false;
                    break;
                }
            }

            if(isRevDiagComplete) {
                return new GameResult(true, firstCharacter);
            }


            int countOfFilledCells = 0;
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(ticTacToeBoard.cells[i][j] != null) {
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells == 9) {
                return new GameResult(false, "-");
            } else {
                return new GameResult(false, "-");
            }
        } else {
            return new GameResult(false, "-");
        }
    }

}

class Board {

}

class TicTacToeBoard extends Board {
    String[][] cells = new String[3][3];

}

class Player {

}

class Move {

}

class GameResult {
    boolean isCompleted;
    String winner;
    public GameResult(boolean isCompleted, String winner) {
        this.isCompleted = isCompleted;
        this.winner = winner;
    }

}