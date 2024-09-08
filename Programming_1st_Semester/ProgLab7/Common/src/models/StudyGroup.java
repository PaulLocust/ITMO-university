package models;

import Reader.ConsoleReader;
import exceptions.FilledIncorrect;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Основной класс Коллекции.
 * Коллекция состоит из экземпляров этого класса StudyGroup
 */
public class StudyGroup implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private long transferredStudents; //Значение поля должно быть больше 0
    private Long averageMark; //Значение поля должно быть больше 0, Поле может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле не может быть null
    private User owner;

    public StudyGroup(int id, String name, Coordinates coordinates, LocalDate creationDate, Long studentsCount, long transferredStudents,
                      Long averageMark, Semester semesterEnum, Person groupAdmin, User owner) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.transferredStudents = transferredStudents;
        this.averageMark = averageMark;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
        this.owner = owner;
    }


    /**
    public StudyGroup(String name, Coordinates coordinates, Long studentsCount, long transferredStudents,
                      Long averageMark, Semester semesterEnum, Person groupAdmin) {
        this.name = name;
        this.coordinates = coordinates;
        this.studentsCount = studentsCount;
        this.transferredStudents = transferredStudents;
        this.averageMark = averageMark;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }
     */
    public StudyGroup() {}

    public static StudyGroup fillStudyGroup(Scanner scanner) {
        StudyGroup studyGroup = new StudyGroup();
        try {
            System.out.println("Entering a StudyGroup object");
            studyGroup.name = (String) ConsoleReader.conditionalRead(scanner, "Enter name: ", false, String::toString, Objects::nonNull, (m) -> !m.equals(""));
            studyGroup.coordinates = Coordinates.fillCoordinates(scanner);
            studyGroup.creationDate = LocalDate.now();
            studyGroup.studentsCount = (Long) ConsoleReader.conditionalRead(scanner, "Enter students count: ", true, Long::parseLong, (m) -> (m == null || Long.parseLong(m) > 0));
            studyGroup.transferredStudents = (long) ConsoleReader.conditionalRead(scanner, "Enter transferred students count: ", false, Long::parseLong,(m) -> Long.parseLong(m) > 0);
            studyGroup.averageMark = (Long) ConsoleReader.conditionalRead(scanner, "Enter average mark: ", true, Long::parseLong, (m) -> (m == null || Long.parseLong(m) > 0));
            studyGroup.semesterEnum = Semester.fillSemester(scanner);
            studyGroup.groupAdmin = Person.fillPerson(scanner);
        } catch (NoSuchElementException e) {
            System.err.println("You haven't fully introduced a StudyGroup!");
        } catch (FilledIncorrect filledIncorrect) {
            filledIncorrect.printStackTrace();
        }
        return studyGroup;
    }


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
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getFormattedCreationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSXXX");
        return getCreationDate().format(formatter);
    }

    /**
     * Установить значение поля creationDate
     * @param creationDate StudyGroup's creationDate
     */
    public void setCreationDate(LocalDate creationDate) {
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return id == that.id && transferredStudents == that.transferredStudents && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && Objects.equals(studentsCount, that.studentsCount) && Objects.equals(averageMark, that.averageMark) && semesterEnum == that.semesterEnum && Objects.equals(groupAdmin, that.groupAdmin) && Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, studentsCount, transferredStudents, averageMark, semesterEnum, groupAdmin, owner);
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", studentsCount=" + studentsCount +
                ", transferredStudents=" + transferredStudents +
                ", averageMark=" + averageMark +
                ", semesterEnum=" + semesterEnum +
                ", groupAdmin=" + groupAdmin +
                ", owner=" + owner +
                '}';
    }
}