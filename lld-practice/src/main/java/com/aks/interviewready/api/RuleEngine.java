package com.aks.interviewready.api;

import com.aks.interviewready.boards.TicTacToeBoard;
import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.GameResult;
import com.aks.interviewready.game.Move;
import com.aks.interviewready.game.Player;

import java.util.HashMap;
import java.util.Map;

// TODO have an Interface RuleEngine<T extends Board> and implement for each type of board, this will be renamed as TicTacToeRuleEngine<TicTacToeBoard>
public class RuleEngine {

    Map<String, RuleSet<TicTacToeBoard>> rules = new HashMap<>();

    public RuleEngine() {
        rules.put(TicTacToeBoard.class.getSimpleName(), TicTacToeBoard.getRuleSet());
    }

    public GameInfo getFork(Board board) {
        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            if (getState(ticTacToeBoard).isOver())
                return GameInfo.builder()
                        .hasFork(false)
                        .build();
            Player[] players = new Player[]{new Player("X"), new Player("O")};
            for (var player : players) {
                if (isFork(player, ticTacToeBoard))
                    return GameInfo.builder()
                            .player(player.flip())
                            .hasFork(true)
                            .build();
            }
            return GameInfo.builder()
                    .hasFork(false)
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid Board Type");
        }
    }

    private boolean isFork(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // make all valid moves as player
                if (board.getSymbol(new Cell(i, j)) == null) {
                    TicTacToeBoard copy = board.copy();
                    copy.move(new Move(new Cell(i, j), player));
                    boolean doesOppWin = false;
                    // make all possible moves for opponent and see if it does not win
                    for (int l = 0; l < 3; l++) {
                        for (int r = 0; r < 3; r++) {
                            if (copy.getSymbol(new Cell(l, r)) == null) {
                                Board copy1 = copy.copy();
                                copy1.move(new Move(new Cell(l, r), player.flip()));
                                if (player.flip().getSymbol().equals(getState(copy1).getWinner())) {
                                    doesOppWin = true;
                                }
                            }
                        }
                    }
                    // opp can be blocked, hence not a fork
                    if (!doesOppWin) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public GameResult getState(Board board) {

        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            for (var rule : rules.get(TicTacToeBoard.class.getSimpleName())) {
                GameResult gameResult = rule.check(ticTacToeBoard);
                if (gameResult.isOver())
                    return gameResult;
            }
            return new GameResult(false, "-");
        } else {
            throw new IllegalArgumentException("Invalid Board Type");
        }
    }

}
