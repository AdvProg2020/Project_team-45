package server.controller.userControllers;

import server.controller.managers.Manager;
import server.model.user.Admin;
import server.model.user.PersonalInfo;
import server.model.user.User;

import java.util.HashMap;

public class AdminController extends UserController implements Manager {
    private static final AdminController instance = new AdminController();

    public static AdminController getInstance() {
        return instance;
    }

    private AdminController() {
        super();
    }


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
