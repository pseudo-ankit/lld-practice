package strategy.headfirst.ducksimulator;

import strategy.headfirst.ducksimulator.behavior.FlyBehavior;
import strategy.headfirst.ducksimulator.behavior.QuackBehavior;

public abstract class Duck {

    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;

    public abstract void display();

    public void doPerformFly() {
        flyBehavior.fly();
    }

    public void doPerformQuack() {
        quackBehavior.quack();
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }
}
