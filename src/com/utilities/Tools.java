package com.utilities;

import com.animals.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

    public static void writeArray(Output output, String[] texts) {
        for (String text : texts) {
            writeString(output, text);
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

    public static void writeString(Output output, String string) {
        try {
            output.consoleWriter.write(string);
            output.consoleWriter.newLine();
            output.consoleWriter.flush();
            if (output.fileWriter != null) {
                output.fileWriter.write(string);
                output.fileWriter.newLine();
                output.fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
