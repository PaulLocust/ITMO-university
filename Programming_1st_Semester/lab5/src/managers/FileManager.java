package managers;

import java.io.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import collection.StudyGroupPackage.StudyGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Этот класс управляет файлами приложения
 */
public class FileManager {

    private String filename;
    private Gson gson;

    public FileManager(String filename) {
        this.filename = filename;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter());
        gson = gsonBuilder.setPrettyPrinting().create();

    }

    /**
     * Установить имя файла, с которым будут производиться манипуляции
     * @param filename имя файл
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Записать коллекцию файл. Используемый формат - json
     * @param collection коллекция элементов
     */
    public void writeCollection(HashSet<StudyGroup> collection) {
        if (!filename.isEmpty()) {
            try (PrintWriter collectionFileWriter = new PrintWriter(filename)) {

                String json = gson.toJson(new ArrayList<>(collection));
                collectionFileWriter.write(json);

                System.out.println("Collection was successfully added to the file!");
            } catch (IOException exception) {
                System.out.println("File is a directory or can't be opened!");
            }
        } else {
            System.out.println("Filename is wrong or corrupted!");
        }
    }

    /**
     * Прочесть коллекцию
     * @return На вывод идёт коллекция, составленная из данных предложенных в файле
     */
    public HashSet<StudyGroup> readCollection() {
        if (!filename.isEmpty()) {
            try (BufferedReader collectionFileReader = new BufferedReader(new FileReader(filename))) {

                StringBuilder json = new StringBuilder();
                String line;
                while ((line = collectionFileReader.readLine()) != null) {
                    json.append(line);
                }
                TypeToken<HashSet<StudyGroup>> typeToken = new TypeToken<HashSet<StudyGroup>>() {};
                HashSet<StudyGroup> hashSet = gson.fromJson(String.valueOf(json), typeToken.getType()); //ArrayList.class
                Validator validator = new Validator(hashSet);

                HashSet<StudyGroup> studyGroupHashSet = new HashSet<>(validator.validate());
                System.out.println("Collection was read successfully!");
                return studyGroupHashSet;
            } catch (FileNotFoundException exception) {
                System.out.println("File was not found!");
            } catch (IOException exception) {
                System.out.println("Error reading from file!");
            } catch (NullPointerException exception) {
                System.out.println("Can't find a collection in file!");
            } catch (IllegalStateException exception) {
                System.out.println("Unexpected error!");
                System.exit(0);
            }
        } else {
            System.out.println("Filename is wrong or corrupted!");
        }
        return new HashSet<>();
    }

    /**
     * Базовый метод для описания класса FileManager
     * @return
     */
    @Override
    public String toString() {
        return "FileManager (class for working with files)";
    }
}


















