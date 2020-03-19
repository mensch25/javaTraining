package tech.timtim.zoo.components.implementation;

import tech.timtim.zoo.components.BaseAnimalComponent;
import tech.timtim.zoo.components.models.AttackResult;
import tech.timtim.zoo.components.models.EatResult;
import tech.timtim.zoo.models.Animal;
import tech.timtim.zoo.models.Bird;

public final class BirdComponent extends BaseAnimalComponent<Bird> {

    public BirdComponent(Bird animal) {
        super(animal);
    }

    @Override
    public AttackResult attack(Animal target) {
        return null;
    }

    @Override
    public EatResult eat(Animal target) {
        return null;
    }
}
