package controller.userControllers;

import controller.InputValidator;
import controller.managers.Creator;
import model.user.Admin;
import model.user.PersonalInfo;

import java.util.HashMap;

public class AdminController extends UserController implements Creator {
    private static AdminController instance = new AdminController();

    public static AdminController getInstance() {
        return instance;
    }

    private AdminController() {
        super();
    }

    public HashMap<String, InputValidator> getNecessaryFieldsToCreate() {
        return super.getNecessaryFieldsToCreate();
    }

    @Override
    public HashMap<String, InputValidator> getOptionalFieldsToCreate() {
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {
        Admin newAdmin = new Admin(new PersonalInfo(filledFeatures));
        market.addUserToList(newAdmin);
    }

    @Override
    public Admin getItemById(String Id) {
        return (Admin) market.getUserByUsername(Id);
    }
}
