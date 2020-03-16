package com.company;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main implements Runnable {

    List<Animal> zoo = new ArrayList<>();
    public static void main(String[] args) { new Main().run(); }

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

        loop:
        while (true){
            writeArray(writer, menuPoints);
            int cmd = readIntOrAction(reader, () -> -1);
            switch (cmd){
                case 1:
                    createAnimal(reader, writer);
                    break;
                case 2:
                    printList(writer);
                    break;
                case 3:
                    attackAnimal(reader, writer);
                    break;
                case 4:
                    eatAnimal(reader, writer);
                    break;
                case 5:
                    break loop;
                default:
                    writeString(writer, "Incorrect command, please try again");
                    break;
            }

        }

    }


    public void createAnimal(BufferedReader reader, BufferedWriter writer){
        String[] menuPoints = {
                "Choose animal type",
                ">1 - Cat",
                ">2 - Mouse",
                ">3 - Bird",
                ">4 - Worm"
        };
        writeArray(writer, menuPoints);
        int cmd = readIntOrAction(reader, () -> -1);
        int age;
        switch (cmd){
            case 1:
                writeString(writer, "Enter age of cat");
                age = readIntOrAction(reader, () -> {
                    writeString(writer, "Incorrect age, cancelled");
                    return -1;
                });
                if (age != -1) {
                    Cat cat = new Cat(age);
                    zoo.add(cat);
                }
                break;

            case 2:
                writeString(writer, "Enter age of mouse");
                age = readIntOrAction(reader, () -> {
                    writeString(writer, "Incorrect age, cancelled");
                    return -1;
                });
                if (age != -1) {
                    Mouse mouse = new Mouse(age);
                    zoo.add(mouse);
                }
                break;

            case 3:
                writeString(writer, "Enter age of bird");
                age = readIntOrAction(reader, () -> {
                    writeString(writer, "Incorrect age, cancelled");
                    return -1;
                });
                if (age != -1) {
                    Bird bird = new Bird(age);
                    zoo.add(bird);
                }
                break;

            case 4:
                writeString(writer, "Enter age of worm");
                age = readIntOrAction(reader, () -> {
                    writeString(writer, "Incorrect age, cancelled");
                    return -1;
                });
                if (age != -1) {
                    Worm worm = new Worm(age);
                    zoo.add(worm);
                }
                break;

            default:
                createAnimal(reader, writer);
                writeString(writer, "Incorrect type of animal, try again");

        }

    }

    public void printList(BufferedWriter writer){
        for (int i = 0; i < zoo.size(); ++i) {
            Animal tmp = zoo.get(i);
            writeString(writer, Integer.toString(i));
            tmp.print(writer);
        }
    }

    public void attackAnimal(BufferedReader reader, BufferedWriter writer){
        writeString(writer, "Choose attacker by index: ");
        int attackerIndex = readIntOrAction(reader, () -> {
            writeString(writer, "Incorrect index, try again");
            return -1;
        });

        writeString(writer, "Choose target by index: ");
        int targetIndex = readIntOrAction(reader, () -> {
            writeString(writer, "Incorrect index, try again");
            return -1;
        });

        if (targetIndex == -1 || attackerIndex == -1) {
            attackAnimal(reader, writer);
        }
        else if (targetIndex == attackerIndex){
            writeString(writer, "Animal cant attack itself, try again");
            attackAnimal(reader, writer);
        }
        else{
            Result result = zoo.get(attackerIndex).attack(zoo.get(targetIndex));
            switch (result){
                case FAIL:
                    writeString(writer, "Attack failed");
                    break;
                case SUCCESS:
                    writeString(writer, "Attack succeed");
                    break;
                case WHAT:
                    writeString(writer, "It wouldn't attack this");
                    break;
            }
        }

    }

    public void eatAnimal(BufferedReader reader, BufferedWriter writer){
        writeString(writer, "Choose feeding animal by index: ");
        int feedingIndex = readIntOrAction(reader, () -> {
            writeString(writer, "Incorrect index, try again");
            return -1;
        });

        writeString(writer, "Choose target by index: ");
        int targetIndex = readIntOrAction(reader, () -> {
            writeString(writer, "Incorrect index, try again");
            return -1;
        });

        if (targetIndex == -1 || feedingIndex == -1) {
            attackAnimal(reader, writer);
        }
        else if (targetIndex == feedingIndex){
            writeString(writer, "Animal cant eat itself, try again");
            attackAnimal(reader, writer);
        }
        else{
            Result result = zoo.get(feedingIndex).eat(zoo.get(targetIndex));
            switch (result){
                case FAIL:
                    writeString(writer, "It cant eat it");
                    break;
                case SUCCESS:
                    writeString(writer, "It ate this");
                    zoo.remove(targetIndex);
                    break;
                case WHAT:
                    writeString(writer, "It wouldn't eat it");
                    break;
            }
        }
    }

    //TOOLS
    public static void writeArray(BufferedWriter writer, String[] texts){
        for (String text : texts) {
            try {
                writer.write(text);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static int readIntOrAction(BufferedReader reader, Supplier<Integer> action){
        try{
            int _int = Integer.parseInt(reader.readLine());
            if (_int >= 0)
                return _int;
            else
                throw new NumberFormatException();
        }
        catch (NumberFormatException | IOException nfeio){
            return action.get();
        }
    }

    public static void writeString(BufferedWriter writer, String string){
        try {
            writer.write(string);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
