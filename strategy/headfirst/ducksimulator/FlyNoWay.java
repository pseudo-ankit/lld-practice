package strategy.headfirst.ducksimulator;

import strategy.headfirst.ducksimulator.behavior.FlyBehavior;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("no way ur flying....");
    }
}
