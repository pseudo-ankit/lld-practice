package com.aks.ducksimulator;

import com.aks.ducksimulator.behavior.QuackBehavior;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
