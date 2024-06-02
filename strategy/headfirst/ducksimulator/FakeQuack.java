package strategy.headfirst.ducksimulator;

import strategy.headfirst.ducksimulator.behavior.QuackBehavior;

public class FakeQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("It's a fake quack");
    }
}
