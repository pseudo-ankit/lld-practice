package com.aks.interviewready.api;

import com.aks.interviewready.game.Player;

public class GameInfo {
    // private final GameResult gameResult;
    private final boolean hasFork;
    private final Player player;

    public GameInfo(Player player, boolean hasFork) {
        this.hasFork = hasFork;
        this.player = player;
        // this.gameResult = gameResult;
    }

    // public GameResult getGameResult() {
    //     return gameResult;
    // }

    public boolean isHasFork() {
        return hasFork;
    }

    public Player getPlayer() {
        return player;
    }
}
