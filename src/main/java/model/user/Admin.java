package model.user;

public class Admin extends User {

    public Admin(PersonalInfo personalInfo) {
        super(personalInfo);
    }

    @Override
    public String getRole() {
        return "admin";
    }
}