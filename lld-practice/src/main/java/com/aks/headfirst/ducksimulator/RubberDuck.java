package com.aks.ducksimulator;

public class RubberDuck extends Duck{

    public RubberDuck() {
        setFlyBehavior(new FlyNoWay());
        setQuackBehavior(new FakeQuack());
    }

    @Override
    public void display() {
        System.out.println("display from RubberDuck");
    }
}
