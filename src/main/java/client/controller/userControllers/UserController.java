package client.controller.userControllers;

import client.network.MethodStringer;

import java.util.ArrayList;
import java.util.HashMap;

public class UserController {
    private static final UserController instance = new UserController();

    protected UserController() {
    }

    public static UserController getInstance() {
        return instance;
    }

    // used in top pane

    public static Boolean isLoggedIn() {
        try {
            return (Boolean) MethodStringer.sampleMethod(UserController.class, "isLoggedIn");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public HashMap<String, String> getUserViewInfo(String username) {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getUserViewInfo", username);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
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

    public void logout() {
        try {
            MethodStringer.sampleMethod(getClass(), "logout");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // used in personal info pane

    public HashMap<String, String> getActiveUserPersonalInfo() {
        try {
            return (HashMap<String, String>) MethodStringer.sampleMethod(getClass(), "getActiveUserPersonalInfo");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public void setPersonalInfoField(String field, String newValue) throws Throwable {
        MethodStringer.sampleMethod(getClass(), "setPersonalInfoField", field, newValue);
    }

    // i dont know!

    public Boolean login(String username, String password) {
        try {
            return (Boolean) MethodStringer.sampleMethod(getClass(), "login", username, password);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
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

    public ArrayList<String> getOnlineUsers() {
        try {
            return (ArrayList<String>) MethodStringer.sampleMethod(getClass(), "getOnlineUsers");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
