package com.utilities;

import com.company.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.function.Supplier;



public class Tools {

    public static AgedAnimal createAgedAnimalByClass(Class clazz, int age) {
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

    public static void writeArray(BufferedWriter writer, String[] texts) {
        for (String text : texts) {
            writeString(writer, text);
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

    public static void writeString(BufferedWriter writer, String string) {
        try {
            writer.write(string);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
