package com.company;

import java.util.Random;




public class Cat extends Animal{

    public Cat(int age) {
        this.type = Type.CAT;
        this.maxAge = 15;
        this.age = age;
        this.state = this.age > this.maxAge ? State.DEAD : State.ALIVE;
    }

    @Override
    public Result attack(Animal target) {
        if (target.getType() == Type.WORM || target.isAlive() == State.DEAD || this.state == State.DEAD){
            return Result.WHAT;
        }
        else {
            int random;
            Result result;
            int coeff;

            if (target.getType() == Type.CAT){
                coeff = 1;
            }
            else if (target.getType() == Type.MOUSE){
                coeff = 5;
            }
            else if (target.getType() == Type.BIRD){
                coeff = 3;
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
            else if (target.getType() == Type.CAT)
                this.setState(State.DEAD);

            return result;
        }
    }

    @Override
    public Result eat(Animal target) {
        if (target.isAlive() == State.ALIVE || this.state == State.DEAD)
            return Result.FAIL;
        else if (target.getType() == Type.WORM || target.getType() == Type.CAT)
            return Result.WHAT;
        else
            return Result.SUCCESS;

    }
}
