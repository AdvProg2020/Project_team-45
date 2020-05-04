package controller;

import model.ProductFilters;
import model.user.User;

import java.util.HashMap;

public class MainController {
    private static MainController instance;
    private boolean loggedIn;

    private MainController() {
        this.loggedIn = false;
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    //================================================================================
    // methods to get a HashMap<String, Object> containing all Objects of a certain type
    //================================================================================

    public HashMap<String, Object> getAllUsersList() {
        return null;
    }

    public HashMap<String, Object> getAllCategoriesList() {
        return null;
    }

    public HashMap<String, Object> getAllProductsList(ProductFilters productFilters) {
        return null;
    }

    public HashMap<String, Object> getAllDiscountCodesList() {
        return null;
    }

    public HashMap<String, Object> getAllOffsList() {
        return null;
    }

    public HashMap<String, Object> getAllRequestsList() {
        return null;
    }

    //================================================================================
    // temporary
    //================================================================================


    public User getActiveUser() {
        return activeUser;
    }
}
