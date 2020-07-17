package server;

public class DatabaseMain {
    public static void main(String[] args) {
        DatabaseController databaseController = new DatabaseController();
//        databaseController.createUsersTable();
        databaseController.writeAnExampleInDatabase();
        databaseController.closeDataBase();
    }
}
