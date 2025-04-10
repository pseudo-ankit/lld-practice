package com.aks.interviewready.boards;

import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Move;
import com.aks.interviewready.game.Player;

public class TicTacToeBoard implements Board {
    private final String[][] cells = new String[3][3];

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().getSymbol());

    }

    @Override
    public String getSymbol(Cell cell) {
        //TODO validations can be added
        return cells[cell.getRow()][cell.getCol()];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\n");
        for (int i = 0; i < 3; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                String cell = this.getSymbol(new Cell(i, j)) == null ? "-" : this.getSymbol(new Cell(i, j));
                builder.append(cell);
                builder.append(" | ");
            }
            s.append(builder, 0, builder.length()-3).append("\n");
        }
        return s.toString();
    }

    @Override
    public Board copy() {
        Board ticTacToeBoard = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Move move = new Move(new Cell(i, j), new Player(this.getSymbol(new Cell(i, j))));
                ticTacToeBoard.move(move);
            }
        }
        return ticTacToeBoard;
    }

    private void setCell(Cell cell, String symbol) {
        if(cells[cell.getRow()][cell.getCol()] != null)
            throw new IllegalArgumentException("Cell is already set (%s, %s)".formatted(cell.getRow(), cell.getRow()));
        cells[cell.getRow()][cell.getCol()] = symbol;
    }
}
