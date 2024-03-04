package collection.comparators;

import collection.StudyGroupPackage.StudyGroup;

import java.util.Comparator;

/**
 * Класс для сравнения 2 объектов класса StudyGroup
 * @see StudyGroup
 */
public class StudyGroupStudCountComparator implements Comparator<StudyGroup> {
    @Override
    public int compare(StudyGroup group1, StudyGroup group2) {
        return group1.getStudentsCount().compareTo(group2.getStudentsCount());
    }
}
