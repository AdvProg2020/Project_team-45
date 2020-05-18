package model.user;

import java.util.HashMap;

public class PersonalInfo {
    private String username;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String password;

    public PersonalInfo(HashMap<String, String> information){
        this.username = information.get("username");
        this.firstName = information.get("first name");
        this.lastName = information.get("last name");
        this.emailAddress = information.get("email address");
        this.phoneNumber = information.get("phone number");
        this.password = information.get("password");
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

    @Override
    public String toString() {
        return  "username:" + username +
                ", firstName:" + firstName +
                ", lastName:" + lastName +
                ", emailAddress:" + emailAddress +
                ", phoneNumber:" + phoneNumber;
    }
}