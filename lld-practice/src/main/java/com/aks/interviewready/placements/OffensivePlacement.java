package com.aks.interviewready.placements;

import com.aks.interviewready.boards.TicTacToeBoard;
import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Move;
import com.aks.interviewready.game.Player;
import com.aks.utils.Utils;

import java.util.Optional;

public class OffensivePlacement implements Placements{
    private static OffensivePlacement instance;

    private OffensivePlacement() {}

    public static OffensivePlacement get() {
        instance = Utils.getIfNull(instance, OffensivePlacement::new);
        return instance;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(offensive(player, board));
    }

    @Override
    public Placements next() {
        return DefensivePlacement.get();
    }

    private Cell offensive(Player player, TicTacToeBoard ticTacToeBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(new Cell(i, j)) == null) {
                    Board copy = ticTacToeBoard.copy();
                    Move move = new Move(new Cell(i, j), player);
                    copy.move(move);
                    // if the player makes this move
                    // either player will win or the game will end (board full)
                    if (Placements.ruleEngine.getState(copy).isOver()) {
                        return new Cell(i, j);
                    }
                }
            }
        }

        return null;
    }
}
