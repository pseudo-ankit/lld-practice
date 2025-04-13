package com.aks.interviewready.api;

import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Player;

/**
 *
 * @param player
 * @param hasFork
 */
public record GameInfo(Player player, boolean hasFork, Cell forkCell) {

    public static GameInfoBuilder builder() {
        return new GameInfoBuilder();
    }

    public static class GameInfoBuilder {
        private boolean hasFork;
        private Player player;
        private Cell forkCell;

        public GameInfoBuilder hasFork(boolean hasFork) {
            this.hasFork = hasFork;
            return this;
        }

        public GameInfoBuilder player(Player player) {
            this.player = player;
            return this;
        }

        public GameInfoBuilder forkCell(Cell forkCell) {
            this.forkCell = forkCell;
            return this;
        }

        public GameInfo build() {
            return new GameInfo(this.player, this.hasFork, this.forkCell);
        }
    }
}
