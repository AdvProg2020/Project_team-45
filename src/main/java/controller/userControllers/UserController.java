package controller.userControllers;

import model.Market;
import model.user.PersonalInfo;
import model.user.User;

import java.util.ArrayList;
import java.util.Arrays;

public class UserController {
    private static final UserController instance = new UserController();
    private final Market market;
    private static User activeUser;
    private static boolean loggedIn;
    private static ArrayList<String> personalInfoFieldsToEdit;

    private UserController() {
        this.market = Market.getInstance();
        personalInfoFieldsToEdit = new ArrayList<>();
        personalInfoFieldsToEdit.addAll(Arrays.asList("firstName", "lastName", "emailAddress", "phoneNumber"
                , "password"));
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

    public static ArrayList<String> getPersonalInfoFieldsToEdit() {
        return personalInfoFieldsToEdit;
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
        return personalInfo.toString();
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
