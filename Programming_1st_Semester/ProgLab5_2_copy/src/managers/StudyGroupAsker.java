package managers;

import collection.StudyGroupPackage.*;
import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;

import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Этот класс нужен для того, чтобы просить пользователя вводить команды и всё остальное
 */
public class StudyGroupAsker {
    CollectionManager collectionManager;
    Scanner userScanner;
    private boolean scriptMode;

    /**
     * Этот метод возвращает объект класса Scanner, который используется для чтения input'а пользователя
     * @return Scanner, который был создан в методе
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Этот метод устанавливает значение userScanner, которое будет использоваться для чтения input'а пользователя
     * @param userScanner Scanner, который будет использоваться для чтения input'а пользователя
     */
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }


    public StudyGroupAsker(CollectionManager collectionManager, Scanner userScanner) {
        this.userScanner = userScanner;
        this.collectionManager = collectionManager;
        scriptMode = false;
    }

    /**
     * Этот метод включает scriptMode
     */
    public void setScriptMode() {
        scriptMode = true;
    }

    /**
     * Этот метод включает userMode, режим становится интерактивным
     */
    public void setUserMode() {
        scriptMode = false;
    }

    /**
     * Установка нового id для коллекции
     * @return id новой коллекции
     */
    public int setId() {
        return collectionManager.generateNewIdForCollection();
    }

    /**
     * Попросить ввести имя для учебной группы
     * @return имя учебной группы
     * @throws IncorrectInputInScriptException
     */
    public String askStudyGroupName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            Console.print("Enter StudyGroup name:");
            Console.print(Console.PS2);
            try {
                name = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(name);
                if (name.isEmpty()) throw new MustBeNotEmptyException();
                break;
            } catch (MustBeNotEmptyException e) {
                Console.printError("The name can't be empty");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NoSuchElementException e) {
                Console.printError("The name can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (IllegalStateException e) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Запросить время создания элемента
     * @return дата создания элемента
     */
    public ZonedDateTime askCreationDate() {
        while (true) {
            try {
                return ZonedDateTime.now();
            } catch (DateTimeException e) {
                Console.printError("Problem with local data");
            }
        }
    }

    /**
     * Попросить ввести количество студентов в StudyGroup
     * @return количество студентов
     * @throws IncorrectInputInScriptException
     */
    public Long askStudentsCount() throws IncorrectInputInScriptException {
        Long studentsCount;
        while (true) {
            try {
                Console.print("Enter Students Count:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();

                if(scriptMode) Console.printLn(s);
                studentsCount = Long.parseLong(s);
                if(s.isEmpty()) return null;
                if(studentsCount <= 0) throw  new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The students count can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                Console.printError("The students count have to be a Long value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            } catch (NotInDeclaredLimitsException e) {
                Console.printError("students count should be positive and more than 0");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }

        }
        return studentsCount;
    }

    /**
     * Попросить ввести количество переведённых студентов в StudyGroup
     * @return количество переведённых студентов
     * @throws IncorrectInputInScriptException
     */
    public long askTransferredStudents() throws IncorrectInputInScriptException {
        long transferStudents;
        while (true) {
            try {
                Console.print("Enter Transferred Students:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();

                if(scriptMode) Console.printLn(s);
                transferStudents = Long.parseLong(s);
                if(transferStudents <= 0) throw  new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The transferred students can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                Console.printError("The transferred students have to be a long value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            } catch (NotInDeclaredLimitsException e) {
                Console.printError("transferred students should be positive and more than 0");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }

        }
        return transferStudents;
    }

    /**
     * Попросить ввести среднюю оценку в StudyGroup
     * @return средняя оценка
     * @throws IncorrectInputInScriptException
     */
    public Long askAverageMark() throws IncorrectInputInScriptException {
        Long averageMark;
        while (true) {
            try {
                Console.print("Enter Average Mark:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();

                if(scriptMode) Console.printLn(s);
                averageMark = Long.parseLong(s);
                if(s.isEmpty()) return null;
                if(averageMark <= 0) throw  new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The average mark can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                Console.printError("The average mark have to be a Long value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            } catch (NotInDeclaredLimitsException e) {
                Console.printError("average mark should be positive and more than 0");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }

        }
        return averageMark;
    }

    /**
     * Попросить ввести номер семестра для StudyGroup
     * @return номер семестра
     * @throws IncorrectInputInScriptException
     */
    public Semester askSemester() throws IncorrectInputInScriptException {
        Semester semester;
        while (true) {
            try {
                Console.printLn("Categories: " + Semester.semesterEnumList());
                Console.print("Enter the semester:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if (s.isEmpty()) throw new MustBeNotEmptyException();
                semester = Semester.valueOf(s.toUpperCase());
                break;
            } catch (MustBeNotEmptyException e){
                Console.printError("The semester can't be empty");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NoSuchElementException exception) {
                Console.printError("Type can't be recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (IllegalArgumentException exception) {
                Console.printError("There is no similar type in category");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return semester;
    }

    /**
     * Попросить ввести значение координаты по оси x для класса Coordinates
     * @return значение координаты по оси x
     * @throws IncorrectInputInScriptException
     */
    private long askCoordinatesX() throws IncorrectInputInScriptException {
        long x;
        while (true) {
            try {
                Console.print("Enter Coordinate X:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                x = Long.parseLong(s);
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The X axis can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                Console.printError("The X axis have to be a long value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return x;
    }

    /**
     * Попросить ввести значение координаты по оси y для класса Coordinates
     * @return значение координаты по оси y
     * @throws IncorrectInputInScriptException
     */
    private Double askCoordinatesY() throws IncorrectInputInScriptException {
        Double y;
        while (true) {
            try {
                Console.print("Enter Coordinate Y:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if (s.isEmpty()) throw new MustBeNotEmptyException();
                y = Double.parseDouble(s);
                if (y > 849) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The Y axis can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if (!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (NotInDeclaredLimitsException e) {
                Console.printError("This variable was overflowed. Y axis must be less than or equal to 849 (Y <= 849)");
            } catch (NumberFormatException e) {
                Console.printError("The Y axis have to be a Double value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            } catch (MustBeNotEmptyException e) {
                Console.printError("The Y axis can't be empty");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        }
        return y;
    }

    /**
     * Запросить новый объект класса Coordinates
     * @return объект класса Coordinates
     * @throws IncorrectInputInScriptException
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        long x;
        Double y;
        x = askCoordinatesX();
        y = askCoordinatesY();
        return new Coordinates(x, y);
    }

    /**
     * Попросить ввести значение координаты по оси x для класса Location
     * @return значение координаты по оси x для учебной группы
     * @throws IncorrectInputInScriptException
     */
    private Double askLocationX() throws IncorrectInputInScriptException {
        Double x;
        while (true) {
            try {
                Console.print("Enter Location coordinate X:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if (s.isEmpty()) throw new MustBeNotEmptyException();
                x = Double.parseDouble(s);
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The X axis can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                Console.printError("The X axis have to be a Double value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            } catch (MustBeNotEmptyException e) {
                Console.printError("The X axis can't be empty");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        }
        return x;
    }

    /**
     * Попросить ввести значение координаты по оси y для класса Location
     * @return значение координаты по оси y
     * @throws IncorrectInputInScriptException
     */
    private Double askLocationY() throws IncorrectInputInScriptException {
        Double y;
        while (true) {
            try {
                Console.print("Enter Location coordinate Y:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if (s.isEmpty()) throw new MustBeNotEmptyException();
                y = Double.parseDouble(s);
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The Y axis can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                Console.printError("The Y axis have to be a Double value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            } catch (MustBeNotEmptyException e) {
                Console.printError("The Y axis can't be empty");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        }
        return y;
    }

    /**
     * Попросить ввести значение координаты по оси z для класса Location
     * @return значение координаты по оси z
     * @throws IncorrectInputInScriptException
     */
    private Long askLocationZ() throws IncorrectInputInScriptException {
        Long z;
        while (true) {
            try {
                Console.print("Enter Location coordinate Z:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if (s.isEmpty()) throw new MustBeNotEmptyException();
                z = Long.parseLong(s);
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The Z axis can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                Console.printError("The Z axis have to be a Long value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            } catch (MustBeNotEmptyException e) {
                Console.printError("The Z axis can't be empty");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        }
        return z;
    }

    /**
     * Попросить ввести имя для класса Location
     * @return имя локации
     * @throws IncorrectInputInScriptException
     */
    private String askLocationName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            Console.print("Enter Location name:");
            Console.print(Console.PS2);
            try {
                name = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(name);
                if(name.isEmpty()) return null;
                break;
            }catch (NoSuchElementException e) {
                Console.printError("The name can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (IllegalStateException e) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Попросить новый объект класса Location
     * @return объект класса Location
     * @throws IncorrectInputInScriptException
     */
    public Location askLocation() throws IncorrectInputInScriptException {
        Double x;
        Double y;
        Long z;
        String name;

        x = askLocationX();
        y = askLocationY();
        z = askLocationZ();
        name = askLocationName();
        return new Location(x, y, z, name);
    }

    /**
     * Попросить ввести имя для класса Person
     * @return имя человека
     * @throws IncorrectInputInScriptException
     */
    public String askPersonName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            Console.print("Enter Person name:");
            Console.print(Console.PS2);
            try {
                name = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(name);
                if (name.isEmpty()) throw new MustBeNotEmptyException();
                break;
            } catch (MustBeNotEmptyException e) {
                Console.printError("The name can't be empty");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NoSuchElementException e) {
                Console.printError("The name can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (IllegalStateException e) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Попросить ввести рост для класса Person
     * @return рост человека
     * @throws IncorrectInputInScriptException
     */
    private float askPersonHeight() throws IncorrectInputInScriptException {
        float height;
        while (true) {
            try {
                Console.print("Enter Person height:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                height = Float.parseFloat(s);
                if (height <= 0) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The height can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                Console.printError("The height have to be a float value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException e) {
                Console.printError("The height value must be positive and bigger than 0 (height > 0)");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return height;
    }

    /**
     * Попросить ввести цвет глаз для класса Person
     * @return цвет глаз человека
     * @throws IncorrectInputInScriptException
     */
    private Color askPersonEyeColor() throws IncorrectInputInScriptException {
        Color eyeColor;
        while (true) {
            try {
                Console.printLn("Categories: " + Color.colorEnumList());
                Console.print("Enter the eye color: ");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if (s.isEmpty()) throw new MustBeNotEmptyException();
                eyeColor = Color.valueOf(s.toUpperCase());
                break;
            } catch (MustBeNotEmptyException e){
                Console.printError("The eyeColor can't be empty");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NoSuchElementException exception) {
                Console.printError("Type can't be recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (IllegalArgumentException exception) {
                Console.printError("There is no similar type in category");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return eyeColor;
    }

    /**
     * Попросить ввести цвет волос для класса Person
     * @return цвет волос человека
     * @throws IncorrectInputInScriptException
     */
    private Color askPersonHairColor() throws IncorrectInputInScriptException {
        Color hairColor;
        while (true) {
            try {
                Console.printLn("Categories: " + Color.colorEnumList());
                Console.print("Enter the hair color: ");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if(s.isEmpty()) return null;
                hairColor = Color.valueOf(s.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Type can't be recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (IllegalArgumentException exception) {
                Console.printError("There is no similar type in category");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return hairColor;
    }

    /**
     * Попросить ввести национальность для класса Person
     * @return национальность человека
     * @throws IncorrectInputInScriptException
     */
    private Country askPersonNationality() throws IncorrectInputInScriptException {
        Country nationality;
        while (true) {
            try {
                Console.printLn("Categories: " + Country.countryEnumList());
                Console.print("Enter the nationality: ");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if(s.isEmpty()) return null;
                nationality = Country.valueOf(s.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Type can't be recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (IllegalArgumentException exception) {
                Console.printError("There is no similar type in category");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return nationality;
    }

    /**
     * Попросить новый объект класса Person
     * @return объект класса Person
     * @throws IncorrectInputInScriptException
     */
    public Person askPerson() throws IncorrectInputInScriptException {
        String name;
        float height;
        Color eyeColor;
        Color hairColor;
        Country nationality;
        Location location;

        name = askPersonName();
        height = askPersonHeight();
        eyeColor = askPersonEyeColor();
        hairColor = askPersonHairColor();
        nationality = askPersonNationality();
        location = askLocation();

        return new Person(name, height, eyeColor, hairColor, nationality, location);
    }


}
