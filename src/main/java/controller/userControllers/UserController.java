package controller.userControllers;

import controller.InputValidator;
import model.Market;
import model.user.AnonymousUser;
import model.user.Buyer;
import model.user.PersonalInfo;
import model.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UserController {
    private static final UserController instance = new UserController();
    protected final Market market;
    private static User activeUser;
    private static AnonymousUser anonymousUser = new AnonymousUser();
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

    public static AnonymousUser getAnonymousUser() {
        return anonymousUser;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static boolean isBuyerLoggedIn() {
        return loggedIn && activeUser.getRole().equals("buyer");
    }

    public static boolean isSellerLoggedIn() {
        return loggedIn && activeUser.getRole().equals("seller");
    }

    public static boolean isAdminLoggedIn() {
        return loggedIn && activeUser.getRole().equals("admin");
    }

    public static ArrayList<String> getPersonalInfoFieldsToEdit() {
        return personalInfoFieldsToEdit;
    }

    public static HashMap<String, String> getUserViewInfo(String username) {
        User showingUser = Market.getInstance().getUserByUsername(username);
        HashMap<String, String> filledMap = new HashMap<>();

        filledMap.put("username", showingUser.getUsername());
        filledMap.put("role", showingUser.getRole());
        filledMap.put("firstName", showingUser.getPersonalInfo().getFirstName());
        filledMap.put("lastName", showingUser.getPersonalInfo().getLastName());
        filledMap.put("phoneNumber", showingUser.getPersonalInfo().getPhoneNumber());
        filledMap.put("email", showingUser.getPersonalInfo().getEmailAddress());
        filledMap.put("avatar", showingUser.getPersonalInfo().getAvatarPath());
        return filledMap;
    }

    // personal info panel

    public void setPersonalInfoField(String field, String newValue) throws Exception{
        PersonalInfo personalInfo = activeUser.getPersonalInfo();
        if (field.equalsIgnoreCase("firstName")) {
            if (!InputValidator.getFirstNameValidator().checkInput(newValue)) {
                throw new Exception("invalid first name format");
            }
            personalInfo.setFirstName(newValue);
        } else if (field.equalsIgnoreCase("lastName")) {
            if (!InputValidator.getFirstNameValidator().checkInput(newValue)) {
                throw new Exception("invalid last name format");
            }
            personalInfo.setLastName(newValue);
        } else if (field.equalsIgnoreCase("emailAddress")) {
            if (!InputValidator.getEmailAddressValidator().checkInput(newValue)) {
                throw new Exception("invalid email address format");
            }
            personalInfo.setEmailAddress(newValue);
        } else if (field.equalsIgnoreCase("phoneNumber")) {
            if (!InputValidator.getPhoneNumberValidator().checkInput(newValue)) {
                throw new Exception("invalid phoneNumber format");
            }
            personalInfo.setPhoneNumber(newValue);
        } else if (field.equalsIgnoreCase("password")) {
            if (!InputValidator.getSimpleTextValidator().checkInput(newValue)) {
                throw new Exception("invalid first name format");
            }
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
            if (activeUser.getRole().equals("buyer")) {
                ((Buyer) activeUser).setCart(anonymousUser.getCart());
            }
            anonymousUser = new AnonymousUser();
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) {
        User loggingInUser = market.getUserByUsername(username);
        if (loggingInUser.checkPassword(password)) {
            loggedIn = true;
            activeUser = loggingInUser;
            if (activeUser.getRole().equals("buyer")) {
                ((Buyer) activeUser).setCart(anonymousUser.getCart());
            }
            anonymousUser = new AnonymousUser();
            return true;
        }
        return false;
    }

    public boolean logout() {
        if (loggedIn) {
            loggedIn = false;
            activeUser = null;
            return true;
        }
        return false;
    }

    public boolean usernameExists(String username) throws UsernameIsRequestException {
        if (market.usernameRequestExists(username))
            throw new UsernameIsRequestException();
        return market.getUserByUsername(username) != null;
    }


    public static void setLoggedIn(boolean loggedIn) {
        UserController.loggedIn = loggedIn;
    }

    public boolean onlyHasAdmin() {
        return Market.getInstance().getAllUsers().size() == 1;
    }
}
