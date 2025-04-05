package com.aks.ducksimulator;

import com.aks.ducksimulator.behavior.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I've got wings to fly");
    }
}
