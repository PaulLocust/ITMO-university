package utility;

import models.StudyGroup;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.stream.Collectors;
/**
 * CollectionManager - это singleton класс, который выполняет роль receiver'а в шаблоне command, здесь реализованы
 * базовые методы для работы команд и обработки коллекции.
 */
public class CollectionManager {

    private HashSet<StudyGroup> studyGroupCollection;

    private ZonedDateTime lastInitTime;
    private ZonedDateTime lastSaveTime;
    private final CollectionFileManager collectionFileManager;

    public CollectionManager(CollectionFileManager collectionFileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.collectionFileManager = collectionFileManager;
        loadCollection();
    }

    /**
     * Получить дату последнего изменения объекта
     * @return дата изменения объекта
     */
    public ZonedDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * Получить дату последнего сохранения объекта
     * @return дата сохранения объекта
     */
    public ZonedDateTime getLastSaveTime() {
        return lastSaveTime;
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
     * @return The first element of the collection or null if collection is empty.
     */
    public StudyGroup getFirst() {
        return studyGroupCollection.stream().findFirst().orElse(null);
    }

    /**
     * Получить элемент коллекции по id
     * @param id уникальный идентификатор элемента коллекции
     * @return элемент коллекции studyGroupCollection или null
     */
    public StudyGroup getById(int id){
        return studyGroupCollection.stream().filter(marine -> marine.getId()==id).findFirst().orElse(null);
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
                .filter(studyGroup -> studyGroup.getId() == id)
                .findFirst()
                .ifPresent(studyGroup -> {
                    studyGroupCollection.remove(studyGroup);
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
        if (studyGroupCollection.isEmpty()) return 1;
        else return
                studyGroupCollection.stream()
                .mapToInt(StudyGroup::getId)
                .filter(studyGroup -> studyGroup >= 0)
                .max()
                .orElse(0) + 1;
    }

    /**
     * Получение информации о коллекции
     * @return список из типа, даты создания и количества элементов коллекции
     */
    public String infoAboutCollection(){
        return "Type - " + studyGroupCollection.getClass() + "\n" +
                "Last Init time - " + getLastInitTime() + "\n" +
                "Last Save time - " + getLastSaveTime() + "\n" +
                "Amount of elements - " + studyGroupCollection.size();
    }

    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
        collectionFileManager.writeCollection(studyGroupCollection);
        lastSaveTime = ZonedDateTime.now();
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection() {
        studyGroupCollection = collectionFileManager.readCollection();
        lastInitTime = ZonedDateTime.now();
    }

    /**
     * @return Name of the collection's type.
     */
    public String collectionType() {
        return studyGroupCollection.getClass().getName();
    }

    /**
     * @return Size of the collection.
     */
    public int collectionSize() {
        return studyGroupCollection.size();
    }

    /**
     * @return Collection content or corresponding string if collection is empty.
     */
    public String showCollection() {
        if (studyGroupCollection.isEmpty()) return "Collection is empty!";
        return studyGroupCollection.stream()
                .map(studyGroup -> studyGroup.toString() + "\n").collect(Collectors.joining());
    }

    /**
     * Remove an organization from the collection
     *
     * @param studyGroup The organization to be removed from the collection.
     */
    public void removeFromCollection(StudyGroup studyGroup){
        studyGroupCollection.remove(studyGroup);
    }
}

