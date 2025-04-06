package com.aks.interviewready.game;

public interface Board {
    void move(Move move);
    Board copy();
    String getSymbol(Cell cell);

}
