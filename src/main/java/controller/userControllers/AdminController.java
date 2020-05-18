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
        HashMap<String, InputValidator> necessaryFields = new HashMap<>();
        // TODO : hatami
        necessaryFields.put("username", InputValidator.getSimpleTextValidator());
        necessaryFields.put("password", InputValidator.getSimpleTextValidator());
        necessaryFields.put("first name", InputValidator.getSimpleTextValidator());
        necessaryFields.put("last name", InputValidator.getSimpleTextValidator());
        necessaryFields.put("email address", InputValidator.getEmailAddressValidator());
        necessaryFields.put("phone number", InputValidator.getSimpleNumberValidator());
        return necessaryFields;
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
        User user = market.getUserByUsername(Id);
        if (user == null || !user.getRole().equals("admin"))
            return null;
        return (Admin) market.getUserByUsername(Id);
    }
}
