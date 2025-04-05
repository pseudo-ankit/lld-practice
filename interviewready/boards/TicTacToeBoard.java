package interviewready.boards;

import interviewready.game.Board;

public class TicTacToeBoard extends Board {
    private String[][] cells = new String[3][3];
    public String getCell(int i, int j) {
        //TODO validations can be added
        return cells[i][j];
    }

}
