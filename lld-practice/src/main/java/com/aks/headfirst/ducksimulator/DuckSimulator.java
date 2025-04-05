package com.aks.ducksimulator;

import java.util.List;

public class DuckSimulator {
    public static void main(String[] args) {
        List.of(new MallardDuck(), new RedHeadDuck(), new DecoyDuck(), new RubberDuck())
                .forEach(duck -> {
                    System.out.printf("############################# From %s #############################%n",
                            duck.getClass().getSimpleName());
                    duck.display();
                    duck.doPerformFly();
                    duck.doPerformQuack();
                    duck.swim();
                });
    }
}
