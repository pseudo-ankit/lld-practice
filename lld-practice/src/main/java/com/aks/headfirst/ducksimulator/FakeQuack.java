package com.aks.ducksimulator;

import com.aks.ducksimulator.behavior.QuackBehavior;

public class FakeQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("It's a fake quack");
    }
}
