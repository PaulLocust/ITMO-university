package models;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Enum Класс Semester предоставляет константы для выбора семестра в классе StudyGroup
 * @see StudyGroup
 */
public enum Semester implements Serializable {
    FIRST,
    SECOND,
    FOURTH,
    FIFTH,
    SIXTH;

    static Semester fillSemester(Scanner scanner) {
        while (true) {
            System.out.println("Enter one of the values: FIRST, SECOND, FOURTH, FIFTH, SIXTH");
            String semester = scanner.nextLine().trim();
            if (semester.equalsIgnoreCase("FIRST")) {
                return FIRST;
            } else if (semester.equalsIgnoreCase("SECOND")) {
                return SECOND;
            } else if (semester.equalsIgnoreCase("FOURTH")) {
                return FOURTH;
            } else if (semester.equalsIgnoreCase("FIFTH")) {
                return FIFTH;
            } else if (semester.equalsIgnoreCase("SIXTH")) {
                return SIXTH;
            } else {
                System.err.println("You have entered a non-existent value.");
            }

        }
    }

    static Semester fillSemesterFromFile(Scanner scanner) {
        while (true) {
            String semester = scanner.nextLine().trim();
            if (semester.equalsIgnoreCase("FIRST")) {
                return FIRST;
            } else if (semester.equalsIgnoreCase("SECOND")) {
                return SECOND;
            } else if (semester.equalsIgnoreCase("FOURTH")) {
                return FOURTH;
            } else if (semester.equalsIgnoreCase("FIFTH")) {
                return FIFTH;
            } else if (semester.equalsIgnoreCase("SIXTH")) {
                return SIXTH;
            } else {
                System.err.println("You have entered a non-existent value.");
            }

        }
    }

}
