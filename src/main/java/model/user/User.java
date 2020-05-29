package model.user;

public abstract class User {
    protected static int newUserId = 1;
    protected String id;
    protected PersonalInfo personalInfo;

    public abstract String getRole();

    public User(PersonalInfo personalInfo) {
        this.id = getRole() + newUserId;
        this.personalInfo = personalInfo;
    }

    public User(String id) {
        this.id = id;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public boolean equals(User otherUser) {
        return this.personalInfo.getUsername().equals(otherUser.getPersonalInfo().getUsername());
    }

    public String getUsername() {
        return this.personalInfo.getUsername();
    }

    public boolean checkPassword(String password) {
        return this.getPersonalInfo().getPassword().equals(password);
    }
}