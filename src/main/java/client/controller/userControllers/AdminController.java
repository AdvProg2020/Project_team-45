package client.controller.userControllers;

import client.network.MethodStringer;

import java.util.HashMap;

public class AdminController extends UserController {
    private static final AdminController instance = new AdminController();

    public static AdminController getInstance() {
        return instance;
    }

    private AdminController() {
        super();
    }

    public void createItem(HashMap<String, String> filledFeatures) {
        try {
            MethodStringer.sampleMethod(getClass(), "createItem", filledFeatures);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
