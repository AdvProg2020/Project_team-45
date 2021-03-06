package server.controller.userControllers;

import server.controller.InputValidator;
import server.controller.ServerManager;
import server.model.Market;
import server.model.user.AnonymousUser;
import server.model.user.Buyer;
import server.model.user.PersonalInfo;
import server.model.user.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserController {
    private static final UserController instance = new UserController();
    protected final Market market;
    private static User activeUser;// hh
    private static AnonymousUser anonymousUser = new AnonymousUser();// hotam
    private static boolean loggedIn; // hh

    protected UserController() {
        this.market = Market.getInstance();
    }

    public static UserController getInstance() {
        return instance;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static String getActiveUserUsername() {
        if (activeUser == null)
            return null;
        return activeUser.getUsername();
    }

    public static AnonymousUser getAnonymousUser() {
        return anonymousUser;
    }

    // used in top pane

    public static Boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        UserController.loggedIn = loggedIn;
    }

    public HashMap<String, String> getUserViewInfo(String username) {
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

    public String getRole() {
        return activeUser.getRole();
    }

    public void logout() {
        loggedIn = false;
        activeUser = null;
    }


    // used in personal info panel
    public HashMap<String, String> getActiveUserPersonalInfo() {
        HashMap<String, String> filledMap = new HashMap<>();
        filledMap.put("username", activeUser.getUsername());
        filledMap.put("role", activeUser.getRole());
        filledMap.put("firstName", activeUser.getPersonalInfo().getFirstName());
        filledMap.put("lastName", activeUser.getPersonalInfo().getLastName());
        filledMap.put("phoneNumber", activeUser.getPersonalInfo().getPhoneNumber());
        filledMap.put("emailAddress", activeUser.getPersonalInfo().getEmailAddress());
        filledMap.put("avatar", activeUser.getPersonalInfo().getAvatarPath());
        return filledMap;
    }

    public void setPersonalInfoField(String field, String newValue) throws Exception {
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


    // i don't know
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

    public boolean usernameExists(String username) throws UsernameIsRequestException {
        if (market.usernameRequestExists(username))
            throw new UsernameIsRequestException();
        return market.getUserByUsername(username) != null;
    }

    public boolean onlyHasAdmin() {
        return Market.getInstance().getAllUsers().size() == 1;
    }

    public static void setActiveUserByUsername(String username) {
        if (username == null) activeUser = null;
        UserController.activeUser = Market.getInstance().getUserByUsername(username);
    }

    public ArrayList<String> getOnlineUsers() {
        return ServerManager.getInstance().getOnlineUsernames();
    }
}
