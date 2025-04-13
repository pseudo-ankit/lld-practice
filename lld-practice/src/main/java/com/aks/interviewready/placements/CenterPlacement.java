package com.aks.interviewready.placements;

import com.aks.interviewready.boards.TicTacToeBoard;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Player;
import com.aks.utils.Utils;

import java.util.Optional;

public class CenterPlacement implements Placements {

    private static CenterPlacement instance;

    private CenterPlacement() {}

    public static CenterPlacement get() {
        instance = Utils.getIfNull(instance, CenterPlacement::new);
        return instance;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.of(new Cell(1, 1))
                .filter(c -> board.getSymbol(c) == null);
    }

    @Override
    public Placements next() {
        return CornerPlacement.get();
    }
}
