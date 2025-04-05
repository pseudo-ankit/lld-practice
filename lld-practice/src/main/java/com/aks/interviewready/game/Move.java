package com.aks.interviewready.game;

public class Move {

    private final Cell cell;
    private final Player player;

    public Player getPlayer() {
        return player;
    }

    public Move(Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }

    public Cell getCell() {
        return this.cell;
    }
}
