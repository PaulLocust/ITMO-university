package models;

import java.io.Serializable;

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

    /**
     * Эта функция возвращает список доступных констант, разделённых запятой
     *
     * @return Список доступных порядковых номеров семестров
     */
    public static String semesterEnumList() {
        StringBuilder nameList = new StringBuilder();
        for (Semester category : values()) {
            nameList.append(category.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}