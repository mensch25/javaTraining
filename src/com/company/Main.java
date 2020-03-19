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
                Tools.writeString(new Output(consoleWriter,null,null), "Cannot open output file");
            }
        Output output = new Output(consoleWriter, fileWriter, jsonWriter);
        MainMenu.reader = reader;

        while (true) {
            Tools.writeString(output, MainMenu.menu.toString());

            int cmd = Tools.readPositiveIntOrAction(reader, () -> -1);
            Consumer<Void> consumer;
            if(cmd == 5 || (consumer = MainMenu.menuMap.get(cmd)) == null) {
                String text = cmd == 5 ? "Good bye" : "Incorrect command, please try again";
                Tools.writeString(output, text);
                break;
            }

            consumer.accept(null);
        }
    }


    public static void createAnimal(BufferedReader reader, Output output) {
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
        Tools.writeString(output, chooseAnimal.toString());

        int cmd = Tools.readPositiveIntOrAction(reader, () -> -1);

        Class clazz = commandMap.get(cmd);
        if (clazz == null) {
            Tools.writeString(output, "Incorrect type of animal, try again");
            createAnimal(reader, output);
            return;
        }

        Tools.writeString(output, "Enter age of " + clazz.getSimpleName());
        int age = Tools.readPositiveIntOrAction(reader, () -> {
            Tools.writeString(output, "Incorrect age, cancelled");
            return -1;
         });
         if (age != -1) {
            Animal animal = Tools.createAnimalByClass(clazz, age);
            zoo.add(animal);
         }
    }

    public static void printList(Output output) {
        for (int i = 0; i < zoo.size(); ++i) {
            Tools.writeString(output, "\nid "+i);
            zoo.get(i).print(output);
        }
    }

    public static void attackAnimal(BufferedReader reader, Output output) {
        Tools.writeString(output, "Choose attacker by index: ");
        int attackerIndex = Tools.readPositiveIntOrAction(reader, () -> {
            Tools.writeString(output, "Incorrect index, try again");
            return -1;
        });

        Tools.writeString(output, "Choose target by index: ");
        int targetIndex = Tools.readPositiveIntOrAction(reader, () -> {
            Tools.writeString(output, "Incorrect index, try again");
            return -1;
        });

        if (targetIndex == -1 || attackerIndex == -1) {
            attackAnimal(reader, output);
        } else if (targetIndex == attackerIndex) {
            Tools.writeString(output, "Animal cant attack itself, try again");
            attackAnimal(reader, output);
        } else {
            Result result = zoo.get(attackerIndex).attack(zoo.get(targetIndex));
            Map<Result, String> resultOfAttack = Map.of(
                Result.FAIL, "Attack failed",
                Result.SUCCESS, "Attack succeed",
                Result.WHAT, "It wouldn't attack this");
            Tools.writeString(output, resultOfAttack.get(result));
        }

    }

    public static void eatAnimal(BufferedReader reader, Output output) {
        Tools.writeString(output, "Choose feeding animal by index: ");
        int feedingIndex = Tools.readPositiveIntOrAction(reader, () -> -1);

        Tools.writeString(output, "Choose target by index: ");
        int targetIndex = Tools.readPositiveIntOrAction(reader, () -> -1);

        if (targetIndex == -1 || feedingIndex == -1) {
            Tools.writeString(output, "Incorrect index, try again");
            attackAnimal(reader, output);
        } else if (targetIndex == feedingIndex) {
            Tools.writeString(output, "Animal cant eat itself, try again");
            attackAnimal(reader, output);
        } else {
            Result result = zoo.get(feedingIndex).eat(zoo.get(targetIndex));
            Map<Result, String> resultOfEating = Map.of(
                Result.FAIL, "It cant eat it",
                Result.SUCCESS, "It ate it",
                Result.WHAT, "It wouldn't eat it");
            Tools.writeString(output, resultOfEating.get(result));
        }
    }

}
