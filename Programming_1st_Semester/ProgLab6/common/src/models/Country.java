package models;

import java.io.Serializable;

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

    /**
     * Эта функция возвращает список доступных констант, разделённых запятой
     *
     * @return Список доступных национальностей
     */
    public static String countryEnumList() {
        StringBuilder nameList = new StringBuilder();
        for (Country category : values()) {
            nameList.append(category.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}