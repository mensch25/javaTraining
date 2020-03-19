package tech.timtim.zoo.models;

public class Cat extends Animal {

    protected int hairColor;

    public Cat(int age, int hairColor) {
        super(age);
        this.maxAge = 15;
        this.isAlive = this.age <= this.maxAge;
        this.hairColor = hairColor;
    }

    public Cat(int age) {
        super(age);
        this.maxAge = 15;
        this.isAlive = this.age <= this.maxAge;
        this.hairColor = 0xFFFF;
    }

    public int getHairColor() {
        return hairColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }
}

