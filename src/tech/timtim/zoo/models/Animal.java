package tech.timtim.zoo.models;


public abstract class Animal {

    protected int age;
    //maxAge is constant in every realisation of @Animal
    protected int maxAge;
    protected boolean isAlive;

    public Animal(int age) {
        this.age = age;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public int getMaxAge() {
        return maxAge;
    }

}
