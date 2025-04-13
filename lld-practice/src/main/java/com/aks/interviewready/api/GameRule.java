package com.aks.interviewready.api;

import com.aks.interviewready.game.CellBoard;
import com.aks.interviewready.game.GameResult;

import java.util.function.Function;

public class GameRule {
    private final Function<CellBoard, GameResult> condition;

    public GameRule(Function<CellBoard, GameResult> condition) {
        this.condition = condition;
    }

    public GameResult check(CellBoard board) {
        return condition.apply(board);
    }
}
