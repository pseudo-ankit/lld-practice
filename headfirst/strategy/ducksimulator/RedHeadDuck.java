package headfirst.strategy.ducksimulator;

public class RedHeadDuck extends Duck{
    public RedHeadDuck() {
        setFlyBehavior(new FlyRocketPowered());
        setQuackBehavior(new MuteQuack());
    }

    @Override
    public void display() {
        System.out.println("display from RedHeadDuck");
    }
}
