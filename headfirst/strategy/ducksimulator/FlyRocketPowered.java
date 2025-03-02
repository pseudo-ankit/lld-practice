package headfirst.strategy.ducksimulator;

import headfirst.strategy.ducksimulator.behavior.FlyBehavior;

public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket");
    }
}
