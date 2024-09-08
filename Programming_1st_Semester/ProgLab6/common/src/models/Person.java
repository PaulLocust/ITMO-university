package models;

import java.io.Serial;
import java.io.Serializable;

/**
 * Этот класс задает поле класса StudyGroup (groupAdmin)
 * @see StudyGroup
 */
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private float height; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле может быть null

    public Person(String name, float height, Color eyeColor, Color hairColor, Country nationality, Location location) {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public Person() {}

    /**
     * Получить имя человека
     * @return значение поля 'name' из класса Person
     */
    public String getName() {
        return name;
    }

    /**
     * Установить значение поля name
     * @param name person's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить рост человека
     * @return значение поля 'height' из класса Person
     */
    public float getHeight() {
        return height;
    }

    /**
     * Установить значение поля height
     * @param height person's height
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Получить цвет глаз человека
     * @return значение поля 'eyeColor' из класса Person
     */
    public Color getEyeColor() {
        return eyeColor;
    }

    /**
     * Установить значение поля eyeColor
     * @param eyeColor person's eye color
     */
    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Получить цвет волос человека
     * @return значение поля 'hairColor' из класса Person
     */
    public Color getHairColor() {
        return hairColor;
    }

    /**
     * Установить значение поля eyeColor
     * @param hairColor person's hairColor
     */
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Получить национальность человека
     * @return значение поля 'name' из класса Person
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * Установить значение поля nationality
     * @param nationality person's nationality
     */
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    /**
     * Получить локацию, в которой находится человек
     * @return значение поля 'location' из класса Person
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Установить значение поля location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Преобразование полей класса Person в удобную для чтения строку
     * @return удобную для чтения строку, состоящую из полей класса Person
     */
    @Override
    public String toString() {
        return "Person: " +
                "name = " + name +
                ", height = " + height +
                ", eyeColor = " + eyeColor +
                ", hairColor = " + hairColor +
                ", nationality = " + nationality +
                ", location = " + location;

    }
}