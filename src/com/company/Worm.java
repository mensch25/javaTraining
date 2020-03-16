package com.company;

public class Worm extends Animal{

    public Worm(int age) {
        this.type = Type.WORM;
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
