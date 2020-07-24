package server.controller;

import server.controller.userControllers.UserController;
import server.model.user.Buyer;
import server.model.user.Seller;

public class BankController {
    private static final BankController instance = new BankController();

    private BankController() {
    }

    public static BankController getInstance() {
        return instance;
    }

    public void chargeWallet(int parseInt) throws Throwable {
        if (UserController.getActiveUser().getRole().equals("buyer")) {
            Buyer buyer = (Buyer) UserController.getActiveUser();
            buyer.chargeWallet(parseInt);
        } else {
            Seller seller = (Seller) UserController.getActiveUser();
            seller.chargeWallet(parseInt);
        }
    }

    public void depositAccount(int parseInt) throws Throwable {
        Seller seller = (Seller) UserController.getActiveUser();
        seller.depositAccount(parseInt);
    }
}