package strategy.headfirst.ducksimulator.behavior;

import strategy.headfirst.ducksimulator.DecoyDuck;
import strategy.headfirst.ducksimulator.MallardDuck;
import strategy.headfirst.ducksimulator.RedHeadDuck;
import strategy.headfirst.ducksimulator.RubberDuck;

import java.util.List;

public class DuckSimulator {
    public static void main(String[] args) {
        List.of(new MallardDuck(), new RedHeadDuck(), new DecoyDuck(), new RubberDuck())
                .forEach(duck -> {
                    System.out.printf("############################# From %s #############################%n",
                            duck.getClass().getSimpleName());
                    duck.display();
                    duck.fly();
                    duck.quack();
                    duck.swim();
                });
    }
}
