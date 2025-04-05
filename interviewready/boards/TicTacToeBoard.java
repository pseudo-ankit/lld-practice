package interviewready.boards;

import interviewready.game.Board;
import interviewready.game.Cell;

public class TicTacToeBoard extends Board {
    private String[][] cells = new String[3][3];
    public String getCell(int i, int j) {
        //TODO validations can be added
        return cells[i][j];
    }

    public void setCell(Cell cell, String symbol) {
        cells[cell.getRow()][cell.getCol()] = symbol;
    }
}
