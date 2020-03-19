package com.company.utilities;

import com.company.Main;

import java.util.Map;
import java.util.function.Consumer;


public class MainMenu {
    public Main main;
    public Menu menu = new Menu("\nWhat u gonna do?",  new String[]{
                "Create new animal",
                "Read zoo list",
                "Attack",
                "Eat",
                "Quit"});

    public Map<Integer, Consumer<Void>> menuMap = Map.of(
            1, v -> main.createAnimal(),
            2, v -> main.printList(),
            3, v -> main.attackAnimal(),
            4, v -> main.eatAnimal());

    @Override
    public String toString() {
        return menu.toString();
    }
}
