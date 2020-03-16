package com.company;

import java.io.BufferedWriter;

enum State {
    DEAD(true),
    ALIVE(false);

    boolean isAlive;

    State(boolean isAlive) {
        this.isAlive = isAlive;
    }
}

enum Type{
    CAT("Cat"),
    MOUSE("Mouse"),
    BIRD("Bird"),
    WORM("Worm");

    String type;

    Type(String type) { this.type = type; }
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
    protected Type type;

    public void setState(State state) {
        this.state = state;
    }

    public State isAlive() {
        return state;
    }

    public int getAge() {
        return age;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public Type getType() {
        return type;
    }

    public void print(BufferedWriter writer){
        String[] attributes = {
                "Type: " + this.type,
                "State: " + this.state,//(this.state == State.ALIVE ? "Alive" : "Dead"),
                "Age: " + this.age,
                "Max age: " + this.maxAge
        };
        Main.writeArray(writer, attributes);
    }

    abstract public Result attack(Animal target);

    abstract public Result eat(Animal target);

}
