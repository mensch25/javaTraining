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
    static public BufferedWriter fileWriter = null;
    static public BufferedWriter jsonWriter = null;

    public static void main(String[] args) {
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
                Tools.writeString(consoleWriter, "Cannot open output file");
            }

        String[] menuPoints = {
                "\nWhat u gonna do?",
                ">1 - Create new animal",
                ">2 - Read zoo list",
                ">3 - Attack",
                ">4 - Eat",
                ">5 - Quit"
        };

        Map<Integer, Consumer<Void>> menuMap = Map.of(
                1, v -> createAnimal(reader, consoleWriter),
                2, v -> printList(consoleWriter),
                3, v -> attackAnimal(reader, consoleWriter),
                4, v -> eatAnimal(reader, consoleWriter));

        while (true) {
            Tools.writeArray(consoleWriter, menuPoints);

            int cmd = Tools.readPositiveIntOrAction(reader, () -> -1);
            Consumer<Void> consumer;
            if(cmd == 5 || (consumer = menuMap.get(cmd)) == null) {
                String text = cmd == 5 ? "Good bye" : "Incorrect command, please try again";
                Tools.writeString(consoleWriter, text);
                break;
            }

            consumer.accept(null);
        }
    }


    public static void createAnimal(BufferedReader reader, BufferedWriter consoleWriter) {
        String[] menuPoints = {
                "Choose animal type",
                ">1 - Cat",
                ">2 - Mouse",
                ">3 - Bird",
                ">4 - Worm"
        };
        Map<Integer, Class> commandMap = Map.of(
                1, Cat.class,
                2, Mouse.class,
                3, Bird.class,
                4, Worm.class);

        Tools.writeArray(consoleWriter, menuPoints);

        int cmd = Tools.readPositiveIntOrAction(reader, () -> -1);

        Class clazz = commandMap.get(cmd);
        if (clazz == null) {
            Tools.writeString(consoleWriter, "Incorrect type of animal, try again");
            createAnimal(reader, consoleWriter);
            return;
        }

        Tools.writeString(consoleWriter, "Enter age of " + clazz.getSimpleName());
        int age = Tools.readPositiveIntOrAction(reader, () -> {
            Tools.writeString(consoleWriter, "Incorrect age, cancelled");
            return -1;
         });
         if (age != -1) {
            Animal animal = Tools.createAnimalByClass(clazz, age);
            zoo.add(animal);
         }
    }

    public static void printList(BufferedWriter consoleWriter) {
        for (int i = 0; i < zoo.size(); ++i) {
            Tools.writeString(consoleWriter, "id "+i);
            zoo.get(i).print(consoleWriter);
        }
    }

    public static void attackAnimal(BufferedReader reader, BufferedWriter consoleWriter) {
        Tools.writeString(consoleWriter, "Choose attacker by index: ");
        int attackerIndex = Tools.readPositiveIntOrAction(reader, () -> {
            Tools.writeString(consoleWriter, "Incorrect index, try again");
            return -1;
        });

        Tools.writeString(consoleWriter, "Choose target by index: ");
        int targetIndex = Tools.readPositiveIntOrAction(reader, () -> {
            Tools.writeString(consoleWriter, "Incorrect index, try again");
            return -1;
        });

        if (targetIndex == -1 || attackerIndex == -1) {
            attackAnimal(reader, consoleWriter);
        } else if (targetIndex == attackerIndex) {
            Tools.writeString(consoleWriter, "Animal cant attack itself, try again");
            attackAnimal(reader, consoleWriter);
        } else {
            Result result = zoo.get(attackerIndex).attack(zoo.get(targetIndex));
            Map<Result, String> resultOfAttack = Map.of(
                Result.FAIL, "Attack failed",
                Result.SUCCESS, "Attack succeed",
                Result.WHAT, "It wouldn't attack this");
            Tools.writeString(consoleWriter, resultOfAttack.get(result));
        }

    }

    public static void eatAnimal(BufferedReader reader, BufferedWriter consoleWriter) {
        Tools.writeString(consoleWriter, "Choose feeding animal by index: ");
        int feedingIndex = Tools.readPositiveIntOrAction(reader, () -> -1);

        Tools.writeString(consoleWriter, "Choose target by index: ");
        int targetIndex = Tools.readPositiveIntOrAction(reader, () -> -1);

        if (targetIndex == -1 || feedingIndex == -1) {
            Tools.writeString(consoleWriter, "Incorrect index, try again");
            attackAnimal(reader, consoleWriter);
        } else if (targetIndex == feedingIndex) {
            Tools.writeString(consoleWriter, "Animal cant eat itself, try again");
            attackAnimal(reader, consoleWriter);
        } else {
            Result result = zoo.get(feedingIndex).eat(zoo.get(targetIndex));
            Map<Result, String> resultOfEating = Map.of(
                Result.FAIL, "It cant eat it",
                Result.SUCCESS, "It ate it",
                Result.WHAT, "It wouldn't eat it");
            Tools.writeString(consoleWriter, resultOfEating.get(result));
        }
    }

}
