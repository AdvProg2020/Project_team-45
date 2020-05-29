package view;

import controller.userControllers.BuyerController;
import controller.userControllers.SellerController;
import model.Market;


public class RegisterPanel extends Panel {
    private static final RegisterPanel instance = new RegisterPanel();
    private final Panel buyerRegisterPanel = ManagingMenu.createItemCreatorPanel("create buyer", BuyerController.getInstance());
    private final Panel sellerRegisterPanel = ManagingMenu.createItemCreatorPanel("create seller", SellerController.getInstance());
    private static String lastRegisterUsername;

    private RegisterPanel() {
        super("Register Panel");
    }

    public static RegisterPanel getInstance() {
        return instance;
    }

    public void execute() {
        String role = matcher.group(1);
        String username = matcher.group(2);
        if (usernameExists(username)){
            System.out.println("username already exists");
            return;
        }
        lastRegisterUsername = username;
        if (role.equals("buyer"))
            buyerRegisterPanel.execute();
        else if (role.equals("seller"))
            sellerRegisterPanel.execute();
    }

    public static String getLastRegisterUsername() {
        return lastRegisterUsername;
    }

    public boolean usernameExists(String username){
        if (username == null)
            return false;
        return Market.getInstance().getUserByUsername(username) != null;
    }
}