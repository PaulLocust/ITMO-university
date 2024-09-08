package collection.StudyGroupPackage;

import java.time.ZonedDateTime;

/**
 * Основной класс Коллекции.
 * Коллекция состоит из экземпляров этого класса StudyGroup
 */
public class StudyGroup implements Comparable<StudyGroup> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private long transferredStudents; //Значение поля должно быть больше 0
    private Long averageMark; //Значение поля должно быть больше 0, Поле может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле не может быть null

    public StudyGroup(int id, String name, Coordinates coordinates, ZonedDateTime creationDate, Long studentsCount, long transferredStudents, Long averageMark, Semester semesterEnum, Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.transferredStudents = transferredStudents;
        this.averageMark = averageMark;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public StudyGroup() {}

    /**
     * Получить id учебной группы
     * @return значение поля 'id' из класса StudyGroup
     */
    public int getId() {
        return id;
    }

    /**
     * Установить значение поля id
     * @param id StudyGroup's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получить имя/название учебной группы
     * @return значение поля 'name' из класса StudyGroup
     */
    public String getName() {
        return name;
    }

    /**
     * Установить значение поля name
     * @param name StudyGroup's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить координаты учебной группы
     * @return значение поля 'coordinates' из класса StudyGroup
     * @see Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Установить значение поля coordinates
     * @param coordinates StudyGroup's coordinates
     * @see Coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Получить дату создания учебной группы
     * @return значение поля 'creationDate' из класса StudyGroup
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Установить значение поля creationDate
     * @param creationDate StudyGroup's creationDate
     */
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Получить число студентов учебной группы
     * @return значение поля 'studentsCount' из класса StudyGroup
     */
    public Long getStudentsCount() {
        return studentsCount;
    }

    /**
     * Установить значение поля studentsCount
     * @param studentsCount StudyGroup's studentsCount
     */
    public void setStudentsCount(Long studentsCount) {
        this.studentsCount = studentsCount;
    }

    /**
     * Получить число переведённых студентов учебной группы
     * @return значение поля 'transferredStudents' из класса StudyGroup
     */
    public long getTransferredStudents() {
        return transferredStudents;
    }

    /**
     * Установить значение поля studentsCount
     * @param transferredStudents StudyGroup's transferredStudents
     */
    public void setTransferredStudents(long transferredStudents) {
        this.transferredStudents = transferredStudents;
    }

    /**
     * Получить среднюю оценку студентов учебной группы
     * @return значение поля 'averageMark' из класса StudyGroup
     */
    public Long getAverageMark() {
        return averageMark;
    }

    /**
     * Установить значение поля averageMark
     * @param averageMark StudyGroup's averageMark
     */
    public void setAverageMark(Long averageMark) {
        this.averageMark = averageMark;
    }

    /**
     * Получить номер семестра студентов учебной группы
     * @return значение поля 'semesterEnum' из класса StudyGroup
     * @see Semester
     */
    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    /**
     * Установить значение поля averageMark
     * @param semesterEnum StudyGroup's semesterEnum
     * @see Semester
     */
    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    /**
     * Получить объект класса руководителя учебной группы
     * @return значение поля 'groupAdmin' из класса StudyGroup
     * @see Person
     */
    public Person getGroupAdmin() {
        return groupAdmin;
    }

    /**
     * Установить значение поля groupAdmin
     * @param groupAdmin StudyGroup's groupAdmin
     * @see Person
     */
    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    /**
     * Метод для сравнения учебных групп по количеству студентов
     * @param o the object to be compared.
     * @return -1, 0, 1
     * @see collection.comparators.StudyGroupStudCountComparator
     */
    @Override
    public int compareTo(StudyGroup o) {
        int result = this.name.compareTo(o.getName());
        return result;
    }

    /**
     * Преобразование полей класса StudyGroup в удобную для чтения строку
     * @return удобную для чтения строку, состоящую из полей класса StudyGroup
     */
    @Override
    public String toString() {
        return "StudyGroup: " +
                "id = " + id +
                ", name = " + name +
                ", coordinates = " + coordinates +
                ", creationDate = " + creationDate +
                ", studentsCount = " + studentsCount +
                ", transferredStudents = " + transferredStudents +
                ", groupAdmin = " + groupAdmin;

    }
}