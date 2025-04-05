package com.aks.ducksimulator.interviewready.boards;

import com.aks.ducksimulator.interviewready.game.Board;
import com.aks.ducksimulator.interviewready.game.Cell;
import com.aks.ducksimulator.interviewready.game.Move;

public class TicTacToeBoard extends Board {
    private String[][] cells = new String[3][3];

    public String getCell(int i, int j) {
        //TODO validations can be added
        return cells[i][j];
    }

    private void setCell(Cell cell, String symbol) {
        cells[cell.getRow()][cell.getCol()] = symbol;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\n");
        for (int i = 0; i < 3; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                String cell = this.getCell(i, j) == null ? "-" : this.getCell(i, j);
                builder.append(cell);
                builder.append(" | ");
            }
            s.append(builder, 0, builder.length()-3).append("\n");
        }
        return s.toString();
    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().getSymbol());

    }
}
