package client.controller.userControllers;

import client.network.ClientSocket;
import client.network.MethodStringer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.model.Market;
import server.model.user.AnonymousUser;
import server.model.user.Buyer;
import server.model.user.User;

import java.lang.reflect.Method;
import java.util.HashMap;

public class UserController {
    private static final UserController instance = new UserController();
    protected final Market market;
    private static User activeUser;
    private static AnonymousUser anonymousUser = new AnonymousUser();
    private static boolean loggedIn;

    protected UserController() {
        this.market = Market.getInstance();
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

    public static Boolean isLoggedIn() {
        try {
            return (Boolean) MethodStringer.sampleMethod(UserController.class, "isLoggedIn");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
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

    public void setPersonalInfoField(String field, String newValue) throws Throwable {
        MethodStringer.sampleMethod(getClass(), "setPersonalInfoField", field, newValue);
    }

    ///

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

    public void logout() {
        try {
            MethodStringer.sampleMethod(getClass(), "logout");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public boolean usernameExists(String username) throws UsernameIsRequestException {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "usernameExists", username);
        } catch (UsernameIsRequestException e) {
            throw e;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public boolean onlyHasAdmin() {
        try {
            return (boolean) MethodStringer.sampleMethod(getClass(), "onlyHasAdmin");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public String getRole() {
        try {
            return (String) MethodStringer.sampleMethod(getClass(), "getRole");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public HashMap<String, String> getActiveUserPersonalInfo() {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, new TypeToken<HashMap<String, String>>(){}.getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
