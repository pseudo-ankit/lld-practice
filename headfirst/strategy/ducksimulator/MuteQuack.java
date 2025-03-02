package headfirst.strategy.ducksimulator;

import headfirst.strategy.ducksimulator.behavior.QuackBehavior;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
