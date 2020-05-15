package model.user;

public abstract class User {
    protected PersonalInfo personalInfo;
    protected String role;

    public abstract String getRole();

    public User(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public boolean equals(User otherUser) {
        return this.personalInfo.getUsername().equals(otherUser.getPersonalInfo().getUsername());
    }

}