package com.aks.interviewready.api;

import com.aks.interviewready.game.Board;
import com.aks.interviewready.game.GameResult;

import java.util.function.Function;

public class GameRule<T extends Board> {
    private final Function<T, GameResult> condition;

    public GameRule(Function<T, GameResult> condition) {
        this.condition = condition;
    }

    public GameResult check(T board) {
        return condition.apply(board);
    }
}
