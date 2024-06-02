package strategy.headfirst.ducksimulator;

public class DecoyDuck extends Duck{
    public DecoyDuck() {
        setFlyBehavior(new FlyRocketPowered());
        setQuackBehavior(new FakeQuack());
    }

    @Override
    public void display() {
        System.out.println("display from DecoyDuck");
    }
}