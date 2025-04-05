package com.aks.interviewready.game;

public class GameResult {
    private final boolean isOver;
    private final String winner;

    public GameResult(boolean isOver, String winner) {
        this.isOver = isOver;
        this.winner = winner;
    }

    public boolean isOver() {
        return isOver;
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "isCompleted=" + isOver +
                ", winner='" + winner + '\'' +
                '}';
    }

    public String getWinner() {
        return winner;
    }
}
