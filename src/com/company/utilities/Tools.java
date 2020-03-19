package com.company.utilities;

import com.company.animals.*;

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
            int parsedInt = Integer.parseInt(InputOutput.inputOutput.reader.readLine());
            if (parsedInt >= 0)
                return parsedInt;

            throw new NumberFormatException();
        } catch (NumberFormatException | IOException nfeio) {
            return action.get();
        }
    }

    public static void writeString(String string) {
        try {
            InputOutput.inputOutput.consoleWriter.write(string);
            InputOutput.inputOutput.consoleWriter.newLine();
            InputOutput.inputOutput.consoleWriter.flush();
            if (InputOutput.inputOutput.fileWriter != null) {
                InputOutput.inputOutput.fileWriter.write(string);
                InputOutput.inputOutput.fileWriter.newLine();
                InputOutput.inputOutput.fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
