package strategy.headfirst.ducksimulator;

import strategy.headfirst.ducksimulator.behavior.QuackBehavior;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
