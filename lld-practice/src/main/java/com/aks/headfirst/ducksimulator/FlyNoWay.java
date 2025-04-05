package com.aks.ducksimulator;

import com.aks.ducksimulator.behavior.FlyBehavior;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("no way ur flying....");
    }
}
