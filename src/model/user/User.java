package model.user;

public abstract class User {
    protected PersonalInfo personalInfo;

    public User(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public abstract String getRole();
}