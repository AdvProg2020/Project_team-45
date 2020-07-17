package server.newModel.nedaei.user;

import server.newModel.DataBaseCompatible;

public abstract class User extends DataBaseCompatible {
    private String username;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private String avatarPath;
    private String role;

    public User(String username, String firstName, String lastName, String emailAddress, String phoneNumber,
                String password, String avatarPath, String role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.avatarPath = avatarPath;
        this.role = role;
    }

    public User(int id, boolean justId) {
        super(id, justId);
    }

    @Override
    protected void readFromDataBase() {

    }

    @Override
    protected void saveToDataBase() {

    }

    public boolean checkPassword(String password) {
        return this.getPassword().equals(password);
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public String getRole() {
        return role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public void setRole(String role) {
        this.role = role;
    }
}