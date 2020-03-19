package com.company.utilities;


public class Menu {
    String message;
    String[] menuPoints;

    public Menu(String message, String[] menuPoints) {
        this.message = message;
        this.menuPoints = menuPoints;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(message);
        for (int i = 0; i < menuPoints.length; i++)
            string
                    .append("\n>")
                    .append(i + 1)
                    .append(' ')
                    .append(menuPoints[i]);
        return string.toString();
    }

}