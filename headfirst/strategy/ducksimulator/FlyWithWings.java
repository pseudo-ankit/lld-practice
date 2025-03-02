package headfirst.strategy.ducksimulator;

import headfirst.strategy.ducksimulator.behavior.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I've got wings to fly");
    }
}
