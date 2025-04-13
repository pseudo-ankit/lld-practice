package com.aks.interviewready.placements;

import com.aks.interviewready.api.GameInfo;
import com.aks.interviewready.boards.TicTacToeBoard;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Player;
import com.aks.utils.Utils;

import java.util.Optional;

public class ForkPlacement implements Placements {
    private static ForkPlacement instance;

    private ForkPlacement() {}

    public static ForkPlacement get() {
        instance = Utils.getIfNull(instance, ForkPlacement::new);
        return instance;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(ruleEngine.getFork(board))
                .filter(GameInfo::hasFork)
                .map(GameInfo::forkCell);
    }

    @Override
    public Placements next() {
        return CenterPlacement.get();
    }
}
