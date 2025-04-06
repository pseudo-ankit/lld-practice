package com.aks.interviewready.game;

public class Player {

    private final String symbol;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    //TODO can we have a TicTacToe Player? since this logic changes with the board
    public Player flip() {
        return new Player(this.symbol.equals("X") ? "O" : "X");
    }
}
