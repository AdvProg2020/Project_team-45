package model.user;

import model.IdKeeper;
import model.IdRecognized;
import model.Savable;

public abstract class User extends IdRecognized implements Savable {
    protected PersonalInfo personalInfo;

    public abstract String getRole();

    public User(PersonalInfo personalInfo) {
        this.id = getRole() + IdKeeper.getInstance().getUsersNewId();
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

    public String getId() {
        return id;
    }
}