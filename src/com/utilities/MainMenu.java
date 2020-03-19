package com.utilities;

import java.io.BufferedReader;
import java.util.Map;
import java.util.function.Consumer;

import static com.company.Main.*;

public class MainMenu extends JsonObject{
    static Output output;
    public static BufferedReader reader;

    public static Menu menu = new Menu("\nWhat u gonna do?",  new String[]{
                "Create new animal",
                "Read zoo list",
                "Attack",
                "Eat",
                "Quit"});

    public static Map<Integer, Consumer<Void>> menuMap = Map.of(
            1, v -> createAnimal(reader, output),
            2, v -> printList(output),
            3, v -> attackAnimal(reader, output),
            4, v -> eatAnimal(reader, output));

}
