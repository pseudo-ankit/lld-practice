package com.aks.interviewready.api;

import com.aks.interviewready.boards.TicTacToeBoard;
import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.GameResult;
import com.aks.interviewready.game.Move;
import com.aks.interviewready.game.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

// TODO have an Interface RuleEngine<T extends Board> and implement for each type of board, this will be renamed as TicTacToeRuleEngine<TicTacToeBoard>
public class RuleEngine {

    Map<String, List<GameRule<TicTacToeBoard>>> rules = new HashMap<>();

    public RuleEngine() {
        var ruleArrayList = new ArrayList<GameRule<TicTacToeBoard>>();
        rules.put(TicTacToeBoard.class.getSimpleName(), ruleArrayList);
        // Row Rule
        ruleArrayList.add(new GameRule<>(board -> isVictorious((i, j) -> board.getSymbol(new Cell(i, j)))));
        // Column Rule
        ruleArrayList.add(new GameRule<>(board -> isVictorious((i, j) -> board.getSymbol(new Cell(j, i)))));
        // Diagonal Rule
        ruleArrayList.add(new GameRule<>(board -> isVictorious(i -> board.getSymbol(new Cell(i, i)))));
        // Reverse Diagonal Rule
        ruleArrayList.add(new GameRule<>(board -> isVictorious(i -> board.getSymbol(new Cell(2 - i, i)))));
        // Board Filled Rule
        ruleArrayList.add(new GameRule<>(this::isBoardFilled));
    }

    public GameInfo getFork(Board board) {
        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            if (getState(ticTacToeBoard).isOver())
                return new GameInfo(null, false);
            Player[] players = new Player[]{new Player("X"), new Player("O")};
            for (var player : players) {
                if (isFork(player, ticTacToeBoard))
                    return new GameInfo(player.flip(), true);
            }
            return new GameInfo(null, false);
        } else {
            throw new IllegalArgumentException("Invalid Board Type");
        }
    }

    private boolean isFork(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // make all valid moves as player
                if (board.getSymbol(new Cell(i, j)) == null) {
                    Board copy = board.copy();
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

    private GameResult isVictorious(Function<Integer, String> getNextSymbol) {
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

    private GameResult isVictorious(BiFunction<Integer, Integer, String> getNextSymbol) {
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

    private GameResult isBoardFilled(TicTacToeBoard board) {
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

    class GameRule<T extends Board> {
        private final Function<T, GameResult> condition;

        GameRule(Function<T, GameResult> condition) {
            this.condition = condition;
        }

        public GameResult check(T board) {
            return condition.apply(board);
        }
    }
}
