package tech.timtim.zoo.models;

public class Worm extends Animal {

    protected double length;

    public Worm(int age) {
        super(age);
        this.maxAge = 3;
        this.length = 0.3;
        this.isAlive = this.age <= this.maxAge;
    }

    public Worm(int age, double length) {
        super(age);
        this.maxAge = 3;
        this.length = length;
        this.isAlive = this.age <= this.maxAge;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}

