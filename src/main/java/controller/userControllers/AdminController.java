package controller.userControllers;

import controller.InputValidator;
import controller.managers.Creator;
import model.user.Admin;
import model.user.PersonalInfo;
import model.user.User;

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

        HashMap<String, InputValidator> necessaryFieldsToCreate = new HashMap<>();
        necessaryFieldsToCreate.put("username", InputValidator.getSimpleTextValidator());
        necessaryFieldsToCreate.putAll(super.getNecessaryFieldsToCreate());
        return necessaryFieldsToCreate;
    }

    @Override
    public HashMap<String, InputValidator> getOptionalFieldsToCreate() {
        return null;
    }

    @Override
    public void createItem(HashMap<String, String> filledFeatures) {
        filledFeatures.put("username", filledFeatures.get("username"));
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
