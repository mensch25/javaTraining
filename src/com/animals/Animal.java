package com.animals;


import com.utilities.InputOutput;
import com.utilities.State;
import com.utilities.Result;
import com.utilities.Tools;

abstract public class Animal {
    protected State state;
    protected int age;
    protected int maxAge;

    public Animal(int age) {
        this.age = age;
    }

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

    public void print(){
        String[] attributes = {
                "Type: " + this.getClass().getSimpleName(),
                "State: " + this.state,
                "Age: " + this.age,
                "Max age: " + this.maxAge
        };
        Tools.writeArray(attributes);
    }


    abstract public Result attack(Animal target);

    abstract public Result eat(Animal target);

}
