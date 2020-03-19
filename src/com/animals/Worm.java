package com.animals;

import com.utilities.State;
import com.utilities.Result;

public class Worm extends Animal {

    public Worm(int age) {
        super(age);
        this.maxAge = 3;
        this.age = age;
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
