package com.utilities;

import java.util.Arrays;

public class Menu {
    String message;
    String[] menuPoints;

    public Menu(String message, String[] menuPoints) {
        this.message = message;
        this.menuPoints = menuPoints;
    }

    @Override
    public String toString() {
        String string = message;
        for (int i = 0; i < menuPoints.length; i++)
            string += "\n>" + (i + 1) + ' ' + menuPoints[i];
        return string;
    }

    public String toJsonString() {

        return "";
    }





    }
