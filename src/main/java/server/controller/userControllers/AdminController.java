package server.controller.userControllers;

import server.model.user.Admin;
import server.model.user.PersonalInfo;
import server.model.user.User;
import server.newModel.bagheri.Supporter;

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
        Admin newAdmin = new Admin(new PersonalInfo(filledFeatures));
        market.addUserToList(newAdmin);
    }

    public void createSupporter(HashMap<String, String> registerFields) {
        Supporter newSupporter = new Supporter(new PersonalInfo(registerFields));
        market.addUserToList(newSupporter);
    }

    public Admin getItemById(String Id) {
        User user = market.getUserByUsername(Id);
        if (user == null || !user.getRole().equals("admin"))
            return null;
        return (Admin) market.getUserByUsername(Id);
    }
}
