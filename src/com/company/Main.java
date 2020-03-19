package com.company;

import com.animals.*;
import com.utilities.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Main {

    static private List<Animal> zoo = new ArrayList<>();

    public static void main(String[] args) {
        BufferedWriter fileWriter = null;
        BufferedWriter jsonWriter = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter consoleWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        if (args.length > 0)
            try {
                if (args[0].equals("-f")) {
                    fileWriter = new BufferedWriter(new FileWriter(args[1]));
                } else if (args[0].equals("-j")) {
                    jsonWriter = new BufferedWriter(new FileWriter(args[1]));
                }
            } catch (IOException e) {
                InputOutput.init(reader, consoleWriter,null,null);
                Tools.writeString("Cannot open output file");
            }

        InputOutput.init(reader, consoleWriter, fileWriter, jsonWriter);

        while (true) {
            Tools.writeString(MainMenu.toText());

            int cmd = Tools.readPositiveIntOrAction(() -> -1);
            Consumer<Void> consumer;
            if(cmd == 5 || (consumer = MainMenu.menuMap.get(cmd)) == null) {
                String text = cmd == 5 ? "Good bye" : "Incorrect command, please try again";
                Tools.writeString(text);
                break;
            }

            consumer.accept(null);
        }
    }


    public static void createAnimal() {
        String[] menuPoints = {
                "Cat",
                "Mouse",
                "Bird",
                "Worm"
        };
        Map<Integer, Class> commandMap = Map.of(
                1, Cat.class,
                2, Mouse.class,
                3, Bird.class,
                4, Worm.class);

        Menu chooseAnimal = new Menu("Choose animal type", menuPoints);
        Tools.writeString(chooseAnimal.toString());

        int cmd = Tools.readPositiveIntOrAction(() -> -1);

        Class clazz = commandMap.get(cmd);
        if (clazz == null) {
            Tools.writeString("Incorrect type of animal, try again");
            createAnimal();
            return;
        }

        Tools.writeString("Enter age of " + clazz.getSimpleName());
        int age = Tools.readPositiveIntOrAction(() -> {
            Tools.writeString("Incorrect age, cancelled");
            return -1;
         });
         if (age != -1) {
            Animal animal = Tools.createAnimalByClass(clazz, age);
            zoo.add(animal);
         }
    }

    public static void printList() {
        for (int i = 0; i < zoo.size(); ++i) {
            Tools.writeString("\nid "+i);
            zoo.get(i).print();
        }
    }

    public static void attackAnimal() {
        Tools.writeString("Choose attacker by index: ");
        int attackerIndex = Tools.readPositiveIntOrAction(() -> {
            Tools.writeString("Incorrect index, try again");
            return -1;
        });

        Tools.writeString("Choose target by index: ");
        int targetIndex = Tools.readPositiveIntOrAction(() -> {
            Tools.writeString("Incorrect index, try again");
            return -1;
        });

        if (targetIndex == -1 || attackerIndex == -1) {
            attackAnimal();
        } else if (targetIndex == attackerIndex) {
            Tools.writeString("Animal cant attack itself, try again");
            attackAnimal();
        } else {
            Result result = zoo.get(attackerIndex).attack(zoo.get(targetIndex));
            Map<Result, String> resultOfAttack = Map.of(
                Result.FAIL, "Attack failed",
                Result.SUCCESS, "Attack succeed",
                Result.WHAT, "It wouldn't attack this");
            Tools.writeString(resultOfAttack.get(result));
        }

    }

    public static void eatAnimal() {
        Tools.writeString("Choose feeding animal by index: ");
        int feedingIndex = Tools.readPositiveIntOrAction(() -> -1);

        Tools.writeString("Choose target by index: ");
        int targetIndex = Tools.readPositiveIntOrAction(() -> -1);

        if (targetIndex == -1 || feedingIndex == -1) {
            Tools.writeString("Incorrect index, try again");
            eatAnimal();
        } else if (targetIndex == feedingIndex) {
            Tools.writeString("Animal cant eat itself, try again");
            eatAnimal();
        } else {
            Result result = zoo.get(feedingIndex).eat(zoo.get(targetIndex));
            Map<Result, String> resultOfEating = Map.of(
                Result.FAIL, "It cant eat it",
                Result.SUCCESS, "It ate it",
                Result.WHAT, "It wouldn't eat it");
            Tools.writeString(resultOfEating.get(result));
        }
    }

}
