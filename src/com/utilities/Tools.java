package com.utilities;

import com.animals.*;
import com.company.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.function.Supplier;

import static com.company.Main.fileWriter;
import static com.company.Main.jsonWriter;


public class Tools {

    public static Animal createAnimalByClass(Class clazz, int age) {
        if (clazz == Cat.class) {
            return new Cat(age);
        } else if (clazz == Mouse.class) {
            return new Mouse(age);
        } else if (clazz == Worm.class) {
            return new Worm(age);
        } else {
            return new Bird(age);
        }
    }

    public static void writeArray(BufferedWriter consoleWriter, String[] texts) {
        for (String text : texts) {
            writeString(consoleWriter, text);
        }
    }

    public static int readPositiveIntOrAction(BufferedReader reader, Supplier<Integer> action) {
        try {
            int parsedInt = Integer.parseInt(reader.readLine());
            if (parsedInt >= 0)
                return parsedInt;

            throw new NumberFormatException();
        } catch (NumberFormatException | IOException nfeio) {
            return action.get();
        }
    }

    public static void writeString(BufferedWriter consoleWriter, String string) {
        try {
            consoleWriter.write(string);
            consoleWriter.newLine();
            consoleWriter.flush();
            if (fileWriter != null) {
                fileWriter.write(string);
                fileWriter.newLine();
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
