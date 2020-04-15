package model.user;

public abstract class User {
    protected PersonalInfo personalInfo;
    public User(String username, String firstName, String lastName, String emailAddress,
                String phoneNumber, String password, String role) {

    }

    public abstract String getRole();
}