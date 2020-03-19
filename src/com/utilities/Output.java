package com.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Output {
    static BufferedWriter fileWriter = null;
    static BufferedWriter jsonWriter = null;
    static BufferedWriter consoleWriter;

    public Output(BufferedWriter consoleWriter, BufferedWriter fileWriter, BufferedWriter jsonWriter) {
        Output.fileWriter = fileWriter;
        Output.jsonWriter = jsonWriter;
        Output.consoleWriter = consoleWriter;
    }



}
