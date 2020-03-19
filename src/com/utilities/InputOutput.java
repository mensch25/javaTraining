package com.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class InputOutput {
    static BufferedWriter fileWriter = null;
    static BufferedWriter jsonWriter = null;
    static BufferedWriter consoleWriter;
    static BufferedReader reader;

    public static void init(BufferedReader reader, BufferedWriter consoleWriter, BufferedWriter fileWriter, BufferedWriter jsonWriter) {
        InputOutput.reader = reader;
        InputOutput.fileWriter = fileWriter;
        InputOutput.jsonWriter = jsonWriter;
        InputOutput.consoleWriter = consoleWriter;
    }



}
