package commands.dataBase;

import models.*;
import exceptions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.sql.*;
import static answers.ErrorAnswer.logger;

public class DataBase {
    private Connection connection;
    // "jdbc:postgresql://localhost:7777/studs" putty
    // "jdbc:postgresql://pg:5432/studs"
    public DataBase(String login, String password) throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:7777/studs", login, password);
        } catch (SQLException e) {
            logger.severe( "Error with database: " + e.getMessage());
        } catch (ClassNotFoundException e){
            logger.severe( "Problem with SQL Driver!");
        }

    }

    public int insert(User user) throws SQLException, NotDatabaseUpdateException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into users (login, password) values (?, ?) returning id");
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());

        try {
            if (preparedStatement.execute()) {
                ResultSet rs = preparedStatement.getResultSet();
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
            throw new NotDatabaseUpdateException("The user object was not added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new NotDatabaseUpdateException("The user object was not added");
        }
    }

    /**
    private int insert(Person groupAdmin) throws SQLException, NotDatabaseUpdateException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "insert into group_admin (name, height, eye_color, hair_color, nationality, " +
                                "x_location_coordinate, y_location_coordinate, z_location_coordinate, location_name) " +
                                "values (?, ?, ?, ?, ?, ?, ?, ?, ?) returning id");
        preparedStatement.setString(1, groupAdmin.getName());
        preparedStatement.setFloat(2, groupAdmin.getHeight());
        preparedStatement.setString(3, groupAdmin.getEyeColor().toString());
        preparedStatement.setString(4, groupAdmin.getHairColor().toString());
        preparedStatement.setString(5, groupAdmin.getNationality().toString());
        preparedStatement.setDouble(6, groupAdmin.getLocation().getX());
        preparedStatement.setDouble(7, groupAdmin.getLocation().getY());
        preparedStatement.setLong(8, groupAdmin.getLocation().getZ());
        preparedStatement.setString(9, groupAdmin.getLocation().getName());

        try {
            if (preparedStatement.execute()) {
                ResultSet rs = preparedStatement.getResultSet();
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
            throw new NotDatabaseUpdateException("The person object was not added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new NotDatabaseUpdateException("The person object was not added");
        }
    }
     */
    public int insert(StudyGroup studyGroup, int userID) throws SQLException, NotDatabaseUpdateException {
        Person groupAdmin = studyGroup.getGroupAdmin();
        System.out.println("user_id = " + userID);
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "insert into study_groups (name, x_coordinate, y_coordinate, " +
                                "creation_date, students_count, transferred_students, average_mark, " +
                                "semester, admin_name, admin_height, admin_eye_color, admin_hair_color," +
                                "admin_nationality, admin_x_location_coordinate, admin_y_location_coordinate, " +
                                "admin_z_location_coordinate, admin_location_name, user_id) " +
                                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning id");

        preparedStatement.setString(1, studyGroup.getName());
        preparedStatement.setLong(2, studyGroup.getCoordinates().getX());
        preparedStatement.setDouble(3, studyGroup.getCoordinates().getY());
        preparedStatement.setDate(4, Date.valueOf(studyGroup.getCreationDate()));
        preparedStatement.setLong(5, studyGroup.getStudentsCount());
        preparedStatement.setLong(6, studyGroup.getTransferredStudents());
        preparedStatement.setLong(7, studyGroup.getAverageMark());
        preparedStatement.setString(8, studyGroup.getSemesterEnum().toString());
        preparedStatement.setString(9, groupAdmin.getName());
        preparedStatement.setFloat(10, groupAdmin.getHeight());
        preparedStatement.setString(11, groupAdmin.getEyeColor().toString());
        preparedStatement.setString(12, groupAdmin.getHairColor().toString());
        preparedStatement.setString(13, groupAdmin.getNationality().toString());
        preparedStatement.setDouble(14, groupAdmin.getLocation().getX());
        preparedStatement.setDouble(15, groupAdmin.getLocation().getY());
        preparedStatement.setLong(16, groupAdmin.getLocation().getZ());
        preparedStatement.setString(17, groupAdmin.getLocation().getName());
        preparedStatement.setInt(18, userID);
        System.out.println("Executing statement: " + preparedStatement);

        try {
            if (preparedStatement.execute()) {
                ResultSet rs = preparedStatement.getResultSet();
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
            throw new NotDatabaseUpdateException("The StudyGroup object was not added");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new NotDatabaseUpdateException("The StudyGroup object was not added");
        }

    }

    public int selectUserID(String login, String password) throws SQLException, UserNotFoundException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from users where login=? and password=?");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        if (preparedStatement.execute()) {
            ResultSet rs = preparedStatement.getResultSet();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }

        throw new UserNotFoundException("The user with this username or password was not found");
    }


    public User selectUser(int id) throws SQLException, UserNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id=?");
        preparedStatement.setInt(1, id);
        if (preparedStatement.execute()) {
            ResultSet rs = preparedStatement.getResultSet();
            if (rs.next()) {
                String login = rs.getString("login");
                String password = rs.getString("password");
                return new User(login, password);
            }
        }

        throw new UserNotFoundException("No user with id = " + id);
    }
    /**
    public Person selectGroupAdmin(int id) throws SQLException, UserNotFoundException, PersonNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from group_admin where id=?");
        preparedStatement.setInt(1, id);
        if (preparedStatement.execute()) {
            ResultSet rs = preparedStatement.getResultSet();
            if (rs.next()) {
                String name = rs.getString("name");
                float height = rs.getFloat("height");
                Color eyeColor = Color.valueOf(rs.getString("eye_color"));
                Color hairColor = Color.valueOf(rs.getString("hair_color"));
                Country nationality = Country.valueOf(rs.getString("nationality"));

                Double x_location_coordinate = rs.getDouble("x_location_coordinate");
                Double y_location_coordinate = rs.getDouble("y_location_coordinate");
                long z_location_coordinate = rs.getLong("z_location_coordinate");
                String location_name = rs.getString("location_name");
                Location location = new Location(x_location_coordinate, y_location_coordinate, z_location_coordinate, location_name);

                return new Person(name, height, eyeColor, hairColor, nationality, location);
            }
        }

        throw new PersonNotFoundException("No person with id = " + id);
    }
    */

    public StudyGroup selectStudyGroup(int id) throws SQLException, StudyGroupNotFound {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from study_groups where id=?");
        preparedStatement.setLong(1, id);
        if (preparedStatement.execute()) {
            ResultSet rs = preparedStatement.getResultSet();
            if (rs.next()) {
                String name = rs.getString("name");
                long x_coordinate = rs.getLong("x_coordinate");
                Double y_coordinate = rs.getDouble("y_coordinate");
                LocalDate creationDate = LocalDate.from(rs.getDate("creation_date").toLocalDate());
                Long students_count = rs.getLong("students_count");
                long transferred_students = rs.getLong("transferred_students");
                Long average_mark = rs.getLong("average_mark");
                Semester semester = Semester.valueOf(rs.getString("semester"));

                String admin_name = rs.getString("admin_name");
                float admin_height = rs.getFloat("admin_height");
                Color admin_eyeColor = Color.valueOf(rs.getString("admin_eye_color"));
                Color admin_hairColor = Color.valueOf(rs.getString("admin_hair_color"));
                Country admin_nationality = Country.valueOf(rs.getString("admin_nationality"));
                Double admin_x_location_coordinate = rs.getDouble("admin_x_location_coordinate");
                Double admin_y_location_coordinate = rs.getDouble("admin_y_location_coordinate");
                long admin_z_location_coordinate = rs.getLong("admin_z_location_coordinate");
                String admin_location_name = rs.getString("admin_location_name");
                int user_id = rs.getInt("user_id");


                try {
                    Coordinates coordinates = new Coordinates(x_coordinate, y_coordinate);
                    Location location = new Location(admin_x_location_coordinate, admin_y_location_coordinate, admin_z_location_coordinate, admin_location_name);
                    Person person = new Person(admin_name, admin_height, admin_eyeColor, admin_hairColor, admin_nationality, location);
                    User user = selectUser(user_id);
                    return new StudyGroup(id, name, coordinates, creationDate, students_count, transferred_students, average_mark, semester, person, user);
                } catch (UserNotFoundException e) {
                    throw new StudyGroupNotFound("Error reading subfields of the studyGroup class");
                } catch (FilledIncorrect e) {
                    throw new RuntimeException(e);
                }
            }
        }

        throw new StudyGroupNotFound("No StudyGroup with id = " + id);
    }


    public HashSet<StudyGroup> selectAllNotes() throws SQLException, NotDatabaseUpdateException, FilledIncorrect {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from study_groups ");

        if (preparedStatement.execute()) {

            ResultSet rs = preparedStatement.getResultSet();
            HashSet<StudyGroup> studyGroups = new HashSet<>();
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                long x_coordinate = rs.getLong("x_coordinate");
                Double y_coordinate = rs.getDouble("y_coordinate");
                LocalDate creationDate = rs.getDate("creation_date").toLocalDate();
                Long students_count = rs.getLong("students_count");
                long transferred_students = rs.getLong("transferred_students");
                Long average_mark = rs.getLong("average_mark");
                Semester semester = Semester.valueOf(rs.getString("semester"));

                String admin_name = rs.getString("admin_name");
                float admin_height = rs.getFloat("admin_height");
                Color admin_eyeColor = Color.valueOf(rs.getString("admin_eye_color"));
                Color admin_hairColor = Color.valueOf(rs.getString("admin_hair_color"));
                Country admin_nationality = Country.valueOf(rs.getString("admin_nationality"));
                Double admin_x_location_coordinate = rs.getDouble("admin_x_location_coordinate");
                Double admin_y_location_coordinate = rs.getDouble("admin_y_location_coordinate");
                long admin_z_location_coordinate = rs.getLong("admin_z_location_coordinate");
                String admin_location_name = rs.getString("admin_location_name");
                int user_id = rs.getInt("user_id");

                try {

                    Coordinates coordinates = new Coordinates(x_coordinate, y_coordinate);
                    Location location = new Location(admin_x_location_coordinate, admin_y_location_coordinate, admin_z_location_coordinate, admin_location_name);
                    Person person = new Person(admin_name, admin_height, admin_eyeColor, admin_hairColor, admin_nationality, location);
                    User user = selectUser(user_id);

                    StudyGroup studyGroup = new StudyGroup(id, name, coordinates, creationDate, students_count,
                            transferred_students, average_mark, semester, person, user);

                    studyGroups.add(studyGroup);

                } catch ( UserNotFoundException e) {
                    throw new NotDatabaseUpdateException("Error in updating the collection");
                } catch (FilledIncorrect filledIncorrect) {
                    filledIncorrect.printStackTrace();
                }
            }
            return studyGroups;
        }
        throw new NotDatabaseUpdateException("Error in updating the collection");
    }

    public void update(int id, StudyGroup studyGroup) throws SQLException, NotDatabaseUpdateException {
        Person groupAdmin = studyGroup.getGroupAdmin();
        PreparedStatement preparedStatement = connection.prepareStatement("update study_groups set " +
                "(name, x_coordinate, y_coordinate,creation_date, students_count, transferred_students, average_mark, " +
                "semester, admin_name, admin_height, admin_eye_color, admin_hair_color, admin_nationality," +
                "admin_x_location_coordinate, admin_y_location_coordinate, admin_z_location_coordinate, " +
                "admin_location_name) = (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) where id=?");
        preparedStatement.setString(1, studyGroup.getName());
        preparedStatement.setLong(2, studyGroup.getCoordinates().getX());
        preparedStatement.setDouble(3, studyGroup.getCoordinates().getY());
        preparedStatement.setDate(4, Date.valueOf(studyGroup.getCreationDate()));
        preparedStatement.setLong(5, studyGroup.getStudentsCount());
        preparedStatement.setLong(6, studyGroup.getTransferredStudents());
        preparedStatement.setLong(7, studyGroup.getAverageMark());
        preparedStatement.setString(8, studyGroup.getSemesterEnum().toString());
        preparedStatement.setString(9, groupAdmin.getName());
        preparedStatement.setFloat(10, groupAdmin.getHeight());
        preparedStatement.setString(11, groupAdmin.getEyeColor().toString());
        preparedStatement.setString(12, groupAdmin.getHairColor().toString());
        preparedStatement.setString(13, groupAdmin.getNationality().toString());
        preparedStatement.setDouble(14, groupAdmin.getLocation().getX());
        preparedStatement.setDouble(15, groupAdmin.getLocation().getY());
        preparedStatement.setLong(16, groupAdmin.getLocation().getZ());
        preparedStatement.setString(17, groupAdmin.getLocation().getName());
        preparedStatement.setInt(18, id);
        preparedStatement.execute();
    }

    public void deleteUserNotes(int userID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from study_groups where user_id=?");
        preparedStatement.setInt(1, userID);
        preparedStatement.execute();
    }

    public void deleteNote(int ID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from study_groups where id=?");
        preparedStatement.setInt(1, ID);
        preparedStatement.execute();
    }

}
