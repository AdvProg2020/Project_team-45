package controller.userControllers;

import controller.InputValidator;
import model.Market;
import model.user.PersonalInfo;
import model.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UserController {
    private static final UserController instance = new UserController();
    protected final Market market;
    private static User activeUser;
    private static boolean loggedIn;
    private static ArrayList<String> personalInfoFieldsToEdit;

    protected UserController() {
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

    public HashMap<String, InputValidator> getNecessaryFieldsToCreate() {
        HashMap<String, InputValidator> necessaryFields = new HashMap<>();
        necessaryFields.put("password", InputValidator.getSimpleTextValidator());
        necessaryFields.put("first name", InputValidator.getSimpleTextValidator());
        necessaryFields.put("last name", InputValidator.getSimpleTextValidator());
        necessaryFields.put("email address", InputValidator.getEmailAddressValidator());
        necessaryFields.put("phone number", InputValidator.getSimpleNumberValidator());
        return necessaryFields;
    }
}