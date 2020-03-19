package tech.timtim.zoo.models;


public class Mouse extends Animal {

    protected float tailLength;

    public Mouse(int age) {
        super(age);
        this.maxAge = 4;
        this.tailLength = 0.2f;
        this.isAlive = this.age <= this.maxAge;
    }

    public Mouse(int age, float tailLength) {
        super(age);
        this.maxAge = 4;
        this.tailLength = tailLength;
        this.isAlive = this.age <= this.maxAge;
    }

    public float getTailLength() {
        return tailLength;
    }

    public void setTailLength(float tailLength) {
        this.tailLength = tailLength;
    }
}