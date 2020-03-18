package com.company;

import java.io.BufferedWriter;



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

    abstract public Result attack(Animal target);

    abstract public Result eat(Animal target);

}
