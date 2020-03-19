package com.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Output {
    BufferedWriter fileWriter = null;
    BufferedWriter jsonWriter = null;
    BufferedWriter consoleWriter;

    public Output(BufferedWriter consoleWriter, BufferedWriter fileWriter, BufferedWriter jsonWriter) {
        this.fileWriter = fileWriter;
        this.jsonWriter = jsonWriter;
        this.consoleWriter = consoleWriter;
    }
}
