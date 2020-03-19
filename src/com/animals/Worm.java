package com.animals;

import com.utilities.State;
import com.utilities.Result;

public class Worm extends Animal {

    protected double length;

    public Worm(int age) {
        super(age);
        this.maxAge = 3;
        this.length = 0.3;
        this.state = this.age > this.maxAge ? State.DEAD : State.ALIVE;
    }

    public Worm(int age, double length) {
        super(age);
        this.maxAge = 3;
        this.length = length;
        this.state = this.age > this.maxAge ? State.DEAD : State.ALIVE;
    }

    @Override
    public Result attack(Animal target) {
        return Result.WHAT;
    }

    @Override
    public Result eat(Animal target) {
        return Result.WHAT;
    }
}
