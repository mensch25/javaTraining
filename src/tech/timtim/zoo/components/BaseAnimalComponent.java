package tech.timtim.zoo.components;

import tech.timtim.zoo.components.models.AttackResult;
import tech.timtim.zoo.components.models.EatResult;
import tech.timtim.zoo.models.Animal;

public abstract class BaseAnimalComponent<T extends Animal> {

    //immutable field
    protected T animal;

    public BaseAnimalComponent(T animal) {
        this.animal = animal;
    }

    public T getAnimal() {
        return animal;
    }

    public abstract AttackResult attack(Animal target);

    public abstract EatResult eat(Animal target);

}