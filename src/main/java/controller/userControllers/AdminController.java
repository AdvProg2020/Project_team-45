package controller.userControllers;

import controller.InputValidator;
import controller.managers.Creator;
import model.user.Admin;
import model.user.PersonalInfo;
import model.user.User;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class AdminController extends UserController implements Creator {
    private static AdminController instance = new AdminController();

    public static AdminController getInstance() {
        return instance;
    }

    private AdminController() {
        super();
    }

    public LinkedHashMap<String, InputValidator> getNecessaryFieldsToCreate() {

        LinkedHashMap<String, InputValidator> necessaryFieldsToCreate = super.getNecessaryFieldsToCreate();
        necessaryFieldsToCreate.put("username", InputValidator.getSimpleTextValidator());
        return necessaryFieldsToCreate;
    }

    @Override
    public LinkedHashMap<String, InputValidator> getOptionalFieldsToCreate() {
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {
        Admin newAdmin = new Admin(new PersonalInfo(filledFeatures));
        market.addUserToList(newAdmin);
    }

    @Override
    public Admin getItemById(String Id) {
        User user = market.getUserByUsername(Id);
        if (user == null || !user.getRole().equals("admin"))
            return null;
        return (Admin) market.getUserByUsername(Id);
    }
}
