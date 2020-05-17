package controller;

import model.*;
import model.user.*;

public class UserController {
    private static final UserController instance = new UserController();
    private final Market market;
    private static User activeUser;
    private static boolean loggedIn;

    private UserController() {
        this.market = Market.getInstance();
    }

    public static UserController getInstance() {
        return instance;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    // personal info panel

    public void setPersonalInfoField(String field, String newValue) {
        PersonalInfo personalInfo = activeUser.getPersonalInfo();
        if (field.equalsIgnoreCase("firstName")) {
            personalInfo.setFirstName(newValue);
        } else if (field.equalsIgnoreCase("lastName")) {
            personalInfo.setLastName(newValue);
        } else if (field.equalsIgnoreCase("emailAddress")) {
            personalInfo.setEmailAddress(newValue);
        } else if (field.equalsIgnoreCase("phoneNumber")) {
            personalInfo.setPhoneNumber(newValue);
        } else if (field.equalsIgnoreCase("password")) {
            personalInfo.setPassword(newValue);
        }
    }

    public String getPersonalInfoDisplay() {
        PersonalInfo personalInfo = activeUser.getPersonalInfo();
        return "username = '" + personalInfo.getUsername() + "'\n" +
                "firstName = '" + personalInfo.getFirstName() + "'\n" +
                "lastName = '" + personalInfo.getLastName() + "'\n" +
                "emailAddress = '" + personalInfo.getEmailAddress() + "'\n" +
                "phoneNumber = '" + personalInfo.getPhoneNumber() + "'";
    }

    ///

    public boolean setActiveUserByUsername(String username) {
        activeUser = market.getUserByUsername(username);
        return activeUser != null;
    }

    public boolean login(String password) {
        if (activeUser.checkPassword(password)) {
            loggedIn = true;
            return true;
        }
        return false;
    }

    public boolean logout() {
        if (loggedIn) {
            loggedIn = false;
            activeUser = null;
        }
        return false;
    }
}
