package models;

import java.io.Serializable;

/**
 * Enum Класс Color предоставляет константы для выбора цвета волос или глаз в классе Person
 * @see Person
 */
public enum Color implements Serializable {
    RED,
    YELLOW,
    ORANGE,
    BLUE;

    /**
     * Эта функция возвращает список доступных констант, разделённых запятой
     *
     * @return Список доступных расцветок глаз или волос
     */
    public static String colorEnumList() {
        StringBuilder nameList = new StringBuilder();
        for (Color category : values()) {
            nameList.append(category.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}