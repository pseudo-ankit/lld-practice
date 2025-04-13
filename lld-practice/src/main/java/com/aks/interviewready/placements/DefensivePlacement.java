package com.aks.interviewready.placements;

import com.aks.interviewready.boards.TicTacToeBoard;
import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Move;
import com.aks.interviewready.game.Player;
import com.aks.utils.Utils;

import java.util.Optional;

public class DefensivePlacement implements Placements {
    private static DefensivePlacement instance;

    private DefensivePlacement() {}

    public static DefensivePlacement get() {
        instance = Utils.getIfNull(instance, DefensivePlacement::new);
        return instance;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(defensive(player, board));
    }

    @Override
    public Placements next() {
        return ForkPlacement.get();
    }

    private Cell defensive(Player player, TicTacToeBoard ticTacToeBoard) {
        Player opponentPlayer = player.flip();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(new Cell(i, j)) == null) {
                    Board copy = ticTacToeBoard.copy();
                    Move move = new Move(new Cell(i, j), opponentPlayer);
                    copy.move(move);
                    // opponent will win with this move, so block it
                    if (ruleEngine.getState(copy).isOver()) {
                        return new Cell(i, j);
                    }
                }
            }
        }

        return null;
    }
}
