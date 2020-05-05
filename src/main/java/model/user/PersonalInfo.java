package model.user;

public class PersonalInfo {
    private String username;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String password;

    public PersonalInfo(String username, String firstName, String lastName, String emailAddress, String phoneNumber, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public void setField(String field, String newValue) {
        if (field.equalsIgnoreCase("firstName")) {
            setFirstName(newValue);
        } else if (field.equalsIgnoreCase("lastName")) {
            setLastName(newValue);
        } else if (field.equalsIgnoreCase("emailAddress")) {
            setEmailAddress(newValue);
        } else if (field.equalsIgnoreCase("phoneNumber")) {
            setPhoneNumber(newValue);
        } else if (field.equalsIgnoreCase("password")) {
            setPassword(newValue);
        } // TODO: else -> throw Exception...
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

}