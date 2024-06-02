package strategy.headfirst.ducksimulator;

public abstract class Duck {
    public abstract void display();

    public void fly() {
        System.out.println("default fly....");
    }

    public void quack() {
        System.out.println("default quack.....");
    }

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }
}
