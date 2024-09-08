package models;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Enum Класс Country предоставляет константы для выбора национальности в классе Person
 * @see Person
 */
public enum Country implements Serializable {
    RUSSIA,
    GERMANY,
    INDIA,
    ITALY,
    THAILAND;

    static Country fillCountry(Scanner scanner) {
        while (true) {
            System.out.println("Enter one of the values: RUSSIA, GERMANY, INDIA, ITALY, THAILAND");
            String country = scanner.nextLine().trim();
            if (country.equalsIgnoreCase("RUSSIA")) {
                return RUSSIA;
            } else if (country.equalsIgnoreCase("GERMANY")) {
                return GERMANY;
            } else if (country.equalsIgnoreCase("INDIA")) {
                return INDIA;
            } else if (country.equalsIgnoreCase("ITALY")) {
                return ITALY;
            } else if (country.equalsIgnoreCase("THAILAND")) {
                return THAILAND;
            } else {
                System.err.println("You have entered a non-existent value.");
            }

        }
    }

    static Country fillCountryFromFile(Scanner scanner) {
        while (true) {
            String country = scanner.nextLine().trim();
            if (country.equalsIgnoreCase("RUSSIA")) {
                return RUSSIA;
            } else if (country.equalsIgnoreCase("GERMANY")) {
                return GERMANY;
            } else if (country.equalsIgnoreCase("INDIA")) {
                return INDIA;
            } else if (country.equalsIgnoreCase("ITALY")) {
                return ITALY;
            } else if (country.equalsIgnoreCase("THAILAND")) {
                return THAILAND;
            } else {
                System.err.println("You have entered a non-existent value.");
            }

        }
    }

}