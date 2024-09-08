package models;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Enum Класс Color предоставляет константы для выбора цвета волос или глаз в классе Person
 * @see Person
 */
public enum Color implements Serializable {
    RED,
    YELLOW,
    ORANGE,
    BLUE;


    static Color fillColor(Scanner scanner) {
        while (true) {
            System.out.println("Enter one of the values: RED, YELLOW, ORANGE, BLUE");
            String color = scanner.nextLine().trim();
            if (color.equalsIgnoreCase("RED")) {
                return RED;
            } else if (color.equalsIgnoreCase("YELLOW")) {
                return YELLOW;
            } else if (color.equalsIgnoreCase("ORANGE")) {
                return ORANGE;
            } else if (color.equalsIgnoreCase("BLUE")) {
                return BLUE;
            } else {
                System.err.println("You have entered a non-existent value.");
            }

        }
    }

    static Color fillColorFromFile(Scanner scanner) {
        while (true) {
            String color = scanner.nextLine().trim();
            if (color.equalsIgnoreCase("RED")) {
                return RED;
            } else if (color.equalsIgnoreCase("YELLOW")) {
                return YELLOW;
            } else if (color.equalsIgnoreCase("ORANGE")) {
                return ORANGE;
            } else if (color.equalsIgnoreCase("BLUE")) {
                return BLUE;
            } else {
                System.err.println("You have entered a non-existent value.");
            }

        }
    }

}