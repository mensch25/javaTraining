package com.company;


import com.utilities.Tools;

import java.io.BufferedWriter;

enum State {
    DEAD(false),
    ALIVE(true);

    boolean isAlive;

    State(boolean isAlive) {
        this.isAlive = isAlive;
    }
}

enum Result{
    SUCCESS(1),
    FAIL(0),
    WHAT(-1);

    int result;

    Result(int res) {
        this.result = res;
    }
}

abstract class Animal {
    protected State state;
    protected int age;
    protected int maxAge;

    public void setState(State state) {
        this.state = state;
    }

    public boolean isAlive() {
        return state.isAlive;
    }

    public int getAge() {
        return age;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void print(BufferedWriter writer){
        String[] attributes = {
                "Type: " + this.getClass().getSimpleName(),
                "State: " + this.state,//(this.state == State.ALIVE ? "Alive" : "Dead"),
                "Age: " + this.age,
                "Max age: " + this.maxAge
        };
        Tools.writeArray(writer, attributes);
    }



    @java.lang.Override
    public java.lang.String toString() {
        return this.getClass().getSimpleName() +
                "state " + state +
                "age " + age +
                "maxAge " + maxAge;
    }

    abstract public Result attack(Animal target);

    abstract public Result eat(Animal target);

}
