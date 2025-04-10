package com.aks.interviewready.api;

import com.aks.interviewready.game.Player;

/**
 *
 * @param player
 * @param hasFork
 */
public record GameInfo(Player player, boolean hasFork) {

    public static GameInfoBuilder builder() {
        return new GameInfoBuilder();
    }

    public static class GameInfoBuilder {
        private boolean hasFork;
        private Player player;

        public GameInfoBuilder hasFork(boolean hasFork) {
            this.hasFork = hasFork;
            return this;
        }

        public GameInfoBuilder player(Player player) {
            this.player = player;
            return this;
        }

        public GameInfo build() {
            return new GameInfo(this.player, this.hasFork);
        }
    }
}
