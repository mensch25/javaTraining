package com.animals;

import com.utilities.State;
import com.utilities.Result;

import java.util.Map;
import java.util.Random;

public class Bird extends Animal {

    protected String sing;

    private static Map<Class, Integer> coeffMap = Map.ofEntries(
            Map.entry(Bird.class, 1),
            Map.entry(Mouse.class, 2),
            Map.entry(Worm.class, 5));

    public Bird(int age) {
        super(age);
        this.maxAge = 5;
        this.sing = "default";
        this.state = this.age > this.maxAge ? State.DEAD : State.ALIVE;
    }

    public Bird(int age, String sing) {
        super(age);
        this.maxAge = 5;
        this.sing = sing;
        this.state = this.age > this.maxAge ? State.DEAD : State.ALIVE;
    }

    @Override
    public Result attack(Animal target) {
        Integer coeff;
        if (target instanceof Cat || !target.isAlive() || this.state == State.DEAD ||
                (coeff = coeffMap.get(target.getClass())) == null) {
            return Result.WHAT;
        }

        int random = new Random().nextInt(coeff * this.age + target.age);
        Result result = random < coeff * this.age ? Result.SUCCESS : Result.FAIL;

        if (result == Result.SUCCESS) {
            target.setState(State.DEAD);
        } else if (target instanceof Bird) {
            this.setState(State.DEAD);
        }

        return result;
    }

    @Override
    public Result eat(Animal target) {
        if (target instanceof Bird || target instanceof Cat || this.state == State.DEAD)
            return Result.WHAT;
        if (target.state == State.ALIVE)
            return Result.FAIL;

        return Result.SUCCESS;
    }

}
