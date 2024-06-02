package strategy.headfirst.ducksimulator;

import strategy.headfirst.ducksimulator.behavior.QuackBehavior;

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
