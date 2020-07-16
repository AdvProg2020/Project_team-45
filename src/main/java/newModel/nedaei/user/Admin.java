package newModel.nedaei.user;

public class Admin extends User {

    public Admin(String username, String firstName, String lastName, String emailAddress, String phoneNumber,
                 String password, String avatarPath, String role) {
        super(username, firstName, lastName, emailAddress, phoneNumber, password, avatarPath, role);
    }

    public Admin(int id, boolean justId) {
        super(id, justId);
    }

    @Override
    protected void readFromDataBase() {

    }

    @Override
    protected void saveToDataBase() {

    }
}
