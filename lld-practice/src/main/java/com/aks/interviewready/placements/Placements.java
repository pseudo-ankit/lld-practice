package com.aks.interviewready.placements;

import com.aks.interviewready.api.RuleEngine;
import com.aks.interviewready.boards.TicTacToeBoard;
import com.aks.interviewready.game.Cell;
import com.aks.interviewready.game.Player;

import java.util.Optional;

public interface Placements {
    RuleEngine ruleEngine = new RuleEngine();
    Optional<Cell> place(TicTacToeBoard board, Player player);
    Placements next();
}
