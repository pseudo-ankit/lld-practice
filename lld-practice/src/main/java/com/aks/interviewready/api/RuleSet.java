package com.aks.interviewready.api;

import com.aks.interviewready.game.Board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RuleSet<T extends Board> implements Iterable<GameRule> {
    private final List<GameRule> rules = new ArrayList<>();

    public void add(GameRule boardGameRule) {
        rules.add(boardGameRule);
    }

    @Override
    public Iterator<GameRule> iterator() {
        return rules.iterator();
    }

    @Override
    public void forEach(Consumer<? super GameRule> action) {
        rules.forEach(action);
    }

    @Override
    public Spliterator<GameRule> spliterator() {
        return rules.spliterator();
    }
}
