package com.company.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class InputOutput {

    BufferedReader reader;
    BufferedWriter consoleWriter;
    BufferedWriter fileWriter = null;
    BufferedWriter jsonWriter = null;

    public static InputOutput inputOutput;

    private InputOutput(){};

    public static void init(BufferedReader reader, BufferedWriter consoleWriter, BufferedWriter fileWriter, BufferedWriter jsonWriter) {
        if (inputOutput == null){
            inputOutput = new InputOutput();
        }

        inputOutput.reader = reader;
        inputOutput.fileWriter = fileWriter;
        inputOutput.jsonWriter = jsonWriter;
        inputOutput.consoleWriter = consoleWriter;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public BufferedWriter getConsoleWriter() {
        return consoleWriter;
    }

    public BufferedWriter getFileWriter() {
        return fileWriter;
    }

    public BufferedWriter getJsonWriter() {
        return jsonWriter;
    }
}
