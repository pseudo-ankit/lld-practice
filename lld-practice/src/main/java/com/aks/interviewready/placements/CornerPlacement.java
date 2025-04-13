package com.aks.interviewready.placements;

import com.aks.interviewready.boards.TicTacToeBoard;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Player;
import com.aks.utils.Utils;

import java.util.Optional;

public class CornerPlacement implements Placements {
    private static CornerPlacement instance;

    private CornerPlacement() {
    }

    public static CornerPlacement get() {
        instance = Utils.getIfNull(instance, CornerPlacement::new);
        return instance;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.of(new int[][]{{0, 0}, {2, 0}, {0, 2}, {2, 2}})
                .map(corners -> {
                    for (int i = 0; i < 4; i++) {
                        if (board.getSymbol(new Cell(corners[i][0], corners[i][1])) == null)
                            return new Cell(corners[i][0], corners[i][1]);
                    }
                    return null;
                });
    }

    @Override
    public Placements next() {
        return null;
    }
}
