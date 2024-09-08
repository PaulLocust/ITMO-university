package models;

import Reader.ConsoleReader;
import exceptions.FilledIncorrect;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

/**
 * Этот класс показывает расположение StudyGroup на оси координат x и y
 */
public class Coordinates implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    private long x;
    private Double y; //Максимальное значение поля: 849, Поле не может быть null

    public Coordinates(long x, Double y) throws FilledIncorrect {
        this.x = x;

        if (y > 849) throw new FilledIncorrect("'y' coordinate must be less than 850");
        this.y = y;
    }

    public static Coordinates fillCoordinates(Scanner scanner) throws NullPointerException, FilledIncorrect {
        System.out.println("Entering the Coordinates object:");
        long x = (long) ConsoleReader.conditionalRead(scanner, "Enter x: ", true, Long::parseLong);
        Double y = (Double) ConsoleReader.conditionalRead(scanner, "Enter y: ", false, Double::parseDouble, Objects::nonNull);
        return new Coordinates(x, y);
    }

    public static Coordinates fillCoordinatesFromFile(Scanner scanner) throws NullPointerException, FilledIncorrect {
        System.out.println("Entering the Coordinates object:");
        long x = (long) ConsoleReader.conditionalRead(scanner, "", true, Long::parseLong);
        Double y = (Double) ConsoleReader.conditionalRead(scanner, "", false, Double::parseDouble, Objects::nonNull);
        return new Coordinates(x, y);
    }
    /**
     * Получить координату x
     *
     * @return значение координаты x
     */
    public long getX() {
        return x;
    }

    /**
     * Получить координату y
     * @return значение координаты y
     */
    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}