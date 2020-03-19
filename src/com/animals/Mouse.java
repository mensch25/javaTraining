package com.animals;


import com.utilities.State;
import com.utilities.Result;

import java.util.Map;
import java.util.Random;

public class Mouse extends Animal {

    protected float tailLength;

    private static Map<Class, Integer> coeffMap = Map.ofEntries(
            Map.entry(Cat.class, 0),
            Map.entry(Mouse.class, 1),
            Map.entry(Worm.class, 5));

    public Mouse(int age) {
        super(age);
        this.maxAge = 4;
        this.tailLength = (float) 0.2;
        this.state = this.age > this.maxAge ? State.DEAD : State.ALIVE;
    }

    public Mouse(int age, float tailLength) {
        super(age);
        this.maxAge = 4;
        this.tailLength = tailLength;
        this.state = this.age > this.maxAge ? State.DEAD : State.ALIVE;
    }

    @Override
    public Result attack(Animal target) {
        Integer coeff;
        if (target instanceof Bird || !target.isAlive() || this.state == State.DEAD ||
                (coeff = coeffMap.get(target.getClass())) == null) {
            return Result.WHAT;
        }

        int random = new Random().nextInt(coeff * this.age + target.age);
        Result result = random < coeff * this.age ? Result.SUCCESS : Result.FAIL;

        if (result == Result.SUCCESS) {
            target.setState(State.DEAD);
        } else if (target instanceof Mouse || target instanceof Cat) {
            this.setState(State.DEAD);
        }

        return result;
    }

    @Override
    public Result eat(Animal target) {
        if (target.state == State.ALIVE || this.state == State.DEAD)
            return Result.FAIL;
        if (target instanceof Bird || target instanceof Cat)
            return Result.WHAT;

        return Result.SUCCESS;
    }
}
