package tech.timtim.zoo.components.implementation;

import tech.timtim.zoo.components.BaseAnimalComponent;
import tech.timtim.zoo.components.models.AttackResult;
import tech.timtim.zoo.components.models.EatResult;
import tech.timtim.zoo.models.Animal;
import tech.timtim.zoo.models.Bird;
import tech.timtim.zoo.models.Worm;

public final class WormComponent extends BaseAnimalComponent<Worm> {

    public WormComponent(Worm animal) {
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
