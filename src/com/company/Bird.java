package com.company;

import java.util.Random;

public class Bird extends Animal{

    public Bird(int age) {
        this.type = Type.BIRD;
        this.maxAge = 5;
        this.age = age;
        this.state = this.age > this.maxAge ? State.DEAD : State.ALIVE;
    }

    @Override
    public Result attack(Animal target) {
        if (target.getType() == Type.CAT || target.isAlive() == State.DEAD || this.state == State.DEAD){
            return Result.WHAT;
        }
        else {
            int random;
            Result result;
            int coeff;

            if (target.getType() == Type.BIRD){
                coeff = 1;
            }
            else if (target.getType() == Type.MOUSE){
                coeff = 2;
            }
            else if (target.getType() == Type.WORM){
                coeff = 5;
            }
            else{
                return Result.WHAT;
            }

            random = new Random().nextInt(coeff*this.age+target.age);

            if (random < coeff*this.age)
                result = Result.SUCCESS;
            else
                result = Result.FAIL;

            if (result == Result.SUCCESS)
                target.setState(State.DEAD);
            else if(target.getType() == Type.BIRD)
                this.setState(State.DEAD);

            return result;
        }
    }

    @Override
    public Result eat(Animal target) {
        if (target.state == State.ALIVE)
            return Result.FAIL;
        else if (target.type == Type.BIRD || target.type == Type.CAT || this.state == State.DEAD)
            return Result.WHAT;
        else
            return Result.SUCCESS;
    }

}
