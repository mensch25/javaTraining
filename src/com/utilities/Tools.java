package com.utilities;

import com.animals.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Supplier;

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

    public static void writeArray(String[] texts) {
        for (String text : texts) {
            writeString(text);
        }
    }

    public static int readPositiveIntOrAction(Supplier<Integer> action) {
        try {
            int parsedInt = Integer.parseInt(InputOutput.reader.readLine());
            if (parsedInt >= 0)
                return parsedInt;

            throw new NumberFormatException();
        } catch (NumberFormatException | IOException nfeio) {
            return action.get();
        }
    }

    public static void writeString(String string) {
        try {
            InputOutput.consoleWriter.write(string);
            InputOutput.consoleWriter.newLine();
            InputOutput.consoleWriter.flush();
            if (InputOutput.fileWriter != null) {
                InputOutput.fileWriter.write(string);
                InputOutput.fileWriter.newLine();
                InputOutput.fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
