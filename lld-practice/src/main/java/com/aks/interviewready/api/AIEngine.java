package com.aks.interviewready.api;

import com.aks.interviewready.boards.TicTacToeBoard;
import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Move;
import com.aks.interviewready.game.Player;

import java.util.Optional;

public class AIEngine {
    private static final int MIN_MOVES = 3;

    public Cell suggestCellToPlay(Player player, Board board) {
        Cell suggestion;
        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            // if moves < MIN_MOVES, AI cannot determine who will win, so start with basic moves
            if (countMoves(ticTacToeBoard) < MIN_MOVES) {
                suggestion = getBasicMove(ticTacToeBoard);
            } else if (countMoves(ticTacToeBoard) < MIN_MOVES+1) {
                suggestion = getSmartMove(player, ticTacToeBoard);
            } else {
                suggestion = getOptimalMove(player, ticTacToeBoard);
            }
        } else {
            throw new IllegalArgumentException("No a valid board type");
        }

        return suggestion;
    }

    private Cell getOptimalMove(Player player, TicTacToeBoard ticTacToeBoard) {
        RuleEngine ruleEngine = new RuleEngine();
        return Optional
                //1. play winning move
                .ofNullable(offensive(ruleEngine, player, ticTacToeBoard))
                //2. opp is winning, block it
                .or(() -> Optional.ofNullable(defensive(ruleEngine, player, ticTacToeBoard)))
                //3. if a fork, play it
                .or(() -> Optional.ofNullable(ruleEngine.getFork(ticTacToeBoard)).filter(GameInfo::hasFork).map(GameInfo::forkCell))
                //4. if center is available, take it
                .or(() -> Optional.of(new Cell(1, 1)).filter(c -> ticTacToeBoard.getSymbol(c) == null))
                //5. if corner is available, take it
                .or(() -> Optional.of(new int[][]{{0, 0}, {2, 0}, {0, 2}, {2, 2}})
                        .map(corners -> {
                            for(int i=0; i<4;i++) {
                                if (ticTacToeBoard.getSymbol(new Cell(corners[i][0], corners[i][1])) == null)
                                    return new Cell(corners[i][0], corners[i][1]);
                            }
                            return null;
                        }))
                .orElse(null);
    }

    private Cell getSmartMove(Player player, TicTacToeBoard ticTacToeBoard) {

        RuleEngine ruleEngine = new RuleEngine();
        Cell cell;
        // check for victorious move
        cell = offensive(ruleEngine, player, ticTacToeBoard);
        if(cell != null) return cell;

        // since the player cannot win, block the opponents move
        cell = defensive(ruleEngine, player, ticTacToeBoard);
        if(cell != null) return cell;

        return null;
    }

    private Cell offensive(RuleEngine ruleEngine, Player player, TicTacToeBoard ticTacToeBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(new Cell(i, j)) == null) {
                    Board copy = ticTacToeBoard.copy();
                    Move move = new Move(new Cell(i, j), player);
                    copy.move(move);
                    // if the player makes this move
                    // either player will win or the game will end (board full)
                    if (ruleEngine.getState(copy).isOver()) {
                        return new Cell(i, j);
                    }
                }
            }
        }

        return null;
    }

    private Cell defensive(RuleEngine ruleEngine, Player player, TicTacToeBoard ticTacToeBoard) {
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

    private int countMoves(TicTacToeBoard ticTacToeBoard) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(new Cell(i, j)) != null) {
                    count++;
                }
            }
        }
        return count;
    }

    private Cell getBasicMove(TicTacToeBoard ticTacToeBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(new Cell(i, j)) == null) {
                    return new Cell(i, j);
                }
            }
        }
        return null;
    }
}
