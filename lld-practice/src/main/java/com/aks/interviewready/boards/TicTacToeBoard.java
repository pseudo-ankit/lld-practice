package com.aks.interviewready.boards;

import com.aks.interviewready.api.GameRule;
import com.aks.interviewready.api.RuleSet;
import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.GameResult;
import com.aks.interviewready.game.Move;
import com.aks.interviewready.game.Player;

import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeBoard implements Board {
    private final String[][] cells = new String[3][3];

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().getSymbol());

    }

    public String getSymbol(Cell cell) {
        // TODO validations can be added
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
            s.append(builder, 0, builder.length() - 3).append("\n");
        }
        return s.toString();
    }

    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Move move = new Move(new Cell(i, j), new Player(this.getSymbol(new Cell(i, j))));
                ticTacToeBoard.move(move);
            }
        }
        return ticTacToeBoard;
    }

    private void setCell(Cell cell, String symbol) {
        if (cells[cell.getRow()][cell.getCol()] != null)
            throw new IllegalArgumentException("Cell is already set (%s, %s)".formatted(cell.getRow(), cell.getRow()));
        cells[cell.getRow()][cell.getCol()] = symbol;
    }

    public static RuleSet<TicTacToeBoard> getRuleSet() {
        RuleSet<TicTacToeBoard> rules = new RuleSet<>();
        // Row Rule
        rules.add(new GameRule<>(board -> isVictorious((i, j) -> board.getSymbol(new Cell(i, j)))));
        // Column Rule
        rules.add(new GameRule<>(board -> isVictorious((i, j) -> board.getSymbol(new Cell(j, i)))));
        // Diagonal Rule
        rules.add(new GameRule<>(board -> isVictorious(i -> board.getSymbol(new Cell(i, i)))));
        // Reverse Diagonal Rule
        rules.add(new GameRule<>(board -> isVictorious(i -> board.getSymbol(new Cell(2 - i, i)))));
        // Board Filled Rule
        rules.add(new GameRule<>(TicTacToeBoard::isBoardFilled));
        return rules;
    }

    private static GameResult isVictorious(Function<Integer, String> getNextSymbol) {
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

    private static GameResult isVictorious(BiFunction<Integer, Integer, String> getNextSymbol) {
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

    private static GameResult isBoardFilled(TicTacToeBoard board) {
        int countOfFilledgetCells = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(new Cell(i, j)) != null) {
                    countOfFilledgetCells++;
                }
            }
        }
        if (countOfFilledgetCells == 9) {
            return new GameResult(true, "-");
        } else {
            return new GameResult(false, "-");
        }
    }
}
