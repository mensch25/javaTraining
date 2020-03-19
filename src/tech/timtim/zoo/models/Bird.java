package tech.timtim.zoo.models;


public class Bird extends Animal {

    protected String sing;

    public Bird(int age) {
        super(age);
        this.maxAge = 5;
        this.sing = "default";
        this.isAlive = this.age <= this.maxAge;
    }

    public Bird(int age, String sing) {
        super(age);
        this.maxAge = 5;
        this.sing = sing;
        this.isAlive = this.age <= this.maxAge;
    }

    public String getSing() {
        return sing;
    }
}