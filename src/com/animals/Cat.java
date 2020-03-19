package com.animals;

import com.utilities.State;
import com.utilities.Result;

import java.util.Map;
import java.util.Random;


public class Cat extends Animal {

    protected int hairColor;

    private static Map<Class, Integer> coeffMap = Map.ofEntries(
            Map.entry(Cat.class, 1),
            Map.entry(Mouse.class, 5),
            Map.entry(Bird.class, 3));

    public Cat(int age, int hairColor) {
        super(age);
        this.maxAge = 15;
        this.state = this.age > this.maxAge ? State.DEAD : State.ALIVE;
        this.hairColor = hairColor;
    }


    public Cat(int age) {
        super(age);
        this.maxAge = 15;
        this.state = this.age > this.maxAge ? State.DEAD : State.ALIVE;
        this.hairColor = 0xFFFF;
    }

    @Override
    public Result attack(Animal target) {
        Integer coeff;
        if (target instanceof Worm || !target.isAlive() || this.state == State.DEAD ||
                (coeff = coeffMap.get(target.getClass())) == null) {
            return Result.WHAT;
        }

        int random = new Random().nextInt(coeff * this.age + target.age);
        Result result = random < coeff * this.age ? Result.SUCCESS : Result.FAIL;

        if (result == Result.SUCCESS) {
            target.setState(State.DEAD);
        } else if (target instanceof Cat) {
            this.setState(State.DEAD);
        }

        return result;
    }

    @Override
    public Result eat(Animal target) {
        if (target.isAlive() || this.state == State.DEAD)
            return Result.FAIL;
        if (target instanceof Worm || target instanceof Cat)
            return Result.WHAT;

        return Result.SUCCESS;

    }
}
