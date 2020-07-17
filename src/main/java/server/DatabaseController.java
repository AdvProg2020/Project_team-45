package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseController {
    private Connection connection;

    public DatabaseController() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "CREATE TABLE Users (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "username TEXT NOT NULL, " +
                    "firstName TEXT, " +
                    "lastName TEXT, " +
                    "emailAddress  TEXT, " +
                    "avatarPath TEXT, " +
                    "password TEXT, " +
                    "role TEXT)";
            statement.executeUpdate(sql);
            statement.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void writeAnExampleInDatabase() {
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "INSERT INTO Users (username, firstName, lastName, emailAddress, avatarPath, password, role) " +
                    "VALUES ('a', 'a', 'a', 'a', 'a', 'a', 'a');";
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDataBase() {
        try {
            connection.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
