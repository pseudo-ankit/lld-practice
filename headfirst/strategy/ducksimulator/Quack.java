package headfirst.strategy.ducksimulator;

import headfirst.strategy.ducksimulator.behavior.QuackBehavior;

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
