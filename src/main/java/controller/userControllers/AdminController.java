package controller.userControllers;

import controller.InputValidator;
import controller.managers.Creator;

import java.util.HashMap;

public class AdminController extends UserController implements Creator {
    private static AdminController instance = new AdminController();

    public static AdminController getInstance() {
        return instance;
    }

    private AdminController() {
    }

    public HashMap<String, InputValidator> getNecessaryFieldsToCreate() {
        // TODO : hatami
        return null;
    }

    @Override
    public HashMap<String, InputValidator> getOptionalFieldsToCreate() {
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {

    }

    @Override
    public Object getItemById(String Id) {
    }
}
