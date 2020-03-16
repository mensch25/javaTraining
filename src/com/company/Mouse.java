package com.company;

import java.util.Random;

public class Mouse extends Animal {

    public Mouse(int age) {
        this.type = Type.MOUSE;
        this.maxAge = 4;
        this.age = age;
        this.state = this.age > this.maxAge ? State.DEAD : State.ALIVE;
    }

    @Override
    public Result attack(Animal target) {
        if (target.getType() == Type.BIRD || target.isAlive() == State.DEAD || this.state == State.DEAD){
            return Result.WHAT;
        }
        else {
            int random;
            Result result;
            int coeff;

            if (target.getType() == Type.CAT){
                coeff = 0;
            }
            else if (target.getType() == Type.MOUSE){
                coeff = 1;
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
            else if(target.getType() == Type.MOUSE || target.getType() == Type.CAT)
                this.setState(State.DEAD);

            return result;
        }
    }

    @Override
    public Result eat(Animal target) {
        if (target.state == State.ALIVE || this.state == State.DEAD)
            return Result.FAIL;
        else if (target.type == Type.BIRD || target.type == Type.CAT)
            return Result.WHAT;
        else
            return Result.SUCCESS;
    }
}
