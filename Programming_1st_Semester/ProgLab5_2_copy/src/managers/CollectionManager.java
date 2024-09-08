package managers;

import collection.StudyGroupPackage.StudyGroup;


import java.time.ZonedDateTime;
import java.util.HashSet;

/**
 * CollectionManager - это singleton класс, который выполняет роль receiver'а в шаблоне command, здесь реализованы
 * базовые методы для работы команд и обработки коллекции.
 */
public class CollectionManager {

    private HashSet<StudyGroup> studyGroupCollection;

    private final ZonedDateTime creationDate;

    public CollectionManager() {
        studyGroupCollection = new HashSet<>();
        creationDate = ZonedDateTime.now();
    }

    /**
     * Получить дату создания объекта
     * @return дата создания объекта
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Этот метод возвращает коллекцию элементов StudyGroup
     * @return HashSet элементов StudyGroup
     */
    public HashSet<StudyGroup> getCollection() {
        return studyGroupCollection;
    }

    /**
     * Этот метод устанавливает полю studyGroupCollection значение коллекции, состоящей из экземпляров StudyGroup
     * @param studyGroupCollection коллекция элементов StudyGroup
     */
    public void setCollection(HashSet<StudyGroup> studyGroupCollection) {
        this.studyGroupCollection = studyGroupCollection;
    }

    /**
     * Этот метод добавляет элемент в коллекцию
     * @param studyGroup объект класса StudyGroup
     */
    public void addToCollection(StudyGroup studyGroup){
        studyGroupCollection.add(studyGroup);
    }

    /**
     * Получить элемент коллекции по id
     * @param id уникальный идентификатор элемента коллекции
     * @return элемент коллекции studyGroupCollection или null
     */
    public StudyGroup getById(int id){
        for (StudyGroup studyGroup: studyGroupCollection) {
            if(studyGroup.getId() == id) return studyGroup;
        }
        return null;
    }

    /**
     * Заменить элемент по его id на обновлённый элемент
     * @param id уникальный идентификатор элемента коллекции
     * @param newValue новое значение элемента, которое будет установлено вместо старого
     */
    public void replaceById(int id, StudyGroup newValue) {
        newValue.setId(id);

        studyGroupCollection
                .stream()
                .filter(organization -> organization.getId() == id)
                .findFirst()
                .ifPresent(organization -> {
                    studyGroupCollection.remove(organization);
                    studyGroupCollection.add(newValue);
                });
    }

    /**
     * Удаление элемента по его id
     * @param id уникальный идентификатор элемента коллекции
     */
    public void removeByIdFromCollection(int id) {
        studyGroupCollection.removeIf(studyGroup -> studyGroup.getId() == id);
    }

    /**
     * Очистка коллекции
     */
    public void clearCollection(){
        studyGroupCollection.clear();
    }

    /**
     * Генерация нового id, выбирается наибольший существующий id и добавляется 1.
     * @return id учебной группы, которая была только что создана. Если коллекция пуста, то возвращается 0
     */
    public int generateNewIdForCollection(){
        int id = studyGroupCollection.stream()
                .mapToInt(StudyGroup::getId)
                .filter(studyGroup -> studyGroup >= 0)
                .max()
                .orElse(0);
        return id + 1;
    }

    /**
     * Получение информации о коллекции
     * @return список из типа, даты создания и количества элементов коллекции
     */
    public String infoAboutCollection(){
        return "Type - " + studyGroupCollection.getClass() + "\n" +
                "Creation date - " + getCreationDate() + "\n" +
                "Amount of elements - " + studyGroupCollection.size();
    }


}
