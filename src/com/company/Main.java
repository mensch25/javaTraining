package com.company;

import com.utilities.Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Main implements Runnable {

    private List<Animal> zoo = new ArrayList<>();

    public static void main(String[] args) {
        new Main().run();
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] menuPoints = {
                "\nWhat u gonna do?",
                ">1 - Create new animal",
                ">2 - Read zoo list",
                ">3 - Attack",
                ">4 - Eat",
                ">5 - Quit"
        };
        Map<Integer, Consumer<Void>> menuMap = Map.of(
                1, v -> createAnimal(reader, writer),
                2, v -> printList(writer),
                3, v -> attackAnimal(reader, writer),
                4, v -> eatAnimal(reader, writer));

        while (true) {
            Tools.writeArray(writer, menuPoints);

            int cmd = Tools.readPositiveIntOrAction(reader, () -> -1);
            Consumer<Void> consumer;
            if(cmd == 5 || (consumer = menuMap.get(cmd)) == null) {
                String text = cmd == 5 ? "Good bye" : "Incorrect command, please try again";
                Tools.writeString(writer, text);
                break;
            }

            consumer.accept(null);
        }
    }


    public void createAnimal(BufferedReader reader, BufferedWriter writer) {
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

        Tools.writeArray(writer, menuPoints);

        int cmd = Tools.readPositiveIntOrAction(reader, () -> -1);

        Class clazz;
        if ((clazz = commandMap.get(cmd)) == null) {
            Tools.writeString(writer, "Incorrect type of animal, try again");
            createAnimal(reader, writer);
            return;
        }

        Tools.writeString(writer, "Enter age of " + clazz.getSimpleName());
        int age = Tools.readPositiveIntOrAction(reader, () -> {
            Tools.writeString(writer, "Incorrect age, cancelled");
            return -1;
         });
         if (age != -1) {
            Animal animal = Tools.createAgedAnimalByClass(clazz, age);
            zoo.add(animal);
         }
    }

    public void printList(BufferedWriter writer) {
        for (int i = 0; i < zoo.size(); ++i) {
            Animal tmp = zoo.get(i);
            Tools.writeString(writer, Integer.toString(i));
            tmp.print(writer);
        }
    }

    public void attackAnimal(BufferedReader reader, BufferedWriter writer) {
        Tools.writeString(writer, "Choose attacker by index: ");
        int attackerIndex = Tools.readPositiveIntOrAction(reader, () -> {
            Tools.writeString(writer, "Incorrect index, try again");
            return -1;
        });

        Tools.writeString(writer, "Choose target by index: ");
        int targetIndex = Tools.readPositiveIntOrAction(reader, () -> {
            Tools.writeString(writer, "Incorrect index, try again");
            return -1;
        });

        if (targetIndex == -1 || attackerIndex == -1) {
            attackAnimal(reader, writer);
        } else if (targetIndex == attackerIndex) {
            Tools.writeString(writer, "Animal cant attack itself, try again");
            attackAnimal(reader, writer);
        } else {
            Result result = zoo.get(attackerIndex).attack(zoo.get(targetIndex));
            Map<Result, String> resultOfAttack = Map.of(
                Result.FAIL, "Attack failed",
                Result.SUCCESS, "Attack succeed",
                Result.WHAT, "It wouldn't attack this");
            Tools.writeString(writer, resultOfAttack.get(result));
        }

    }

    public void eatAnimal(BufferedReader reader, BufferedWriter writer) {
        Tools.writeString(writer, "Choose feeding animal by index: ");
        int feedingIndex = Tools.readPositiveIntOrAction(reader, () -> -1);

        Tools.writeString(writer, "Choose target by index: ");
        int targetIndex = Tools.readPositiveIntOrAction(reader, () -> -1);

        if (targetIndex == -1 || feedingIndex == -1) {
            Tools.writeString(writer, "Incorrect index, try again");
            attackAnimal(reader, writer);
        } else if (targetIndex == feedingIndex) {
            Tools.writeString(writer, "Animal cant eat itself, try again");
            attackAnimal(reader, writer);
        } else {
            Result result = zoo.get(feedingIndex).eat(zoo.get(targetIndex));
            Map<Result, String> resultOfEating = Map.of(
                Result.FAIL, "It cant eat it",
                Result.SUCCESS, "It ate it",
                Result.WHAT, "It wouldn't eat it");
            Tools.writeString(writer, resultOfEating.get(result));
        }
    }

}
