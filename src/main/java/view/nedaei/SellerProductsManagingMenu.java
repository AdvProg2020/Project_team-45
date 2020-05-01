package view.nedaei;

import model.user.Seller;
import view.hatami.ManagingMenu;
import view.nedaei.personalinfopanel.PersonalInfoPanel;

public class SellerProductsManagingMenu extends ManagingMenu {
    private static SellerProductsManagingMenu instance;

    private SellerProductsManagingMenu() {
        super("seller products managing page", null);
    }

    public static SellerProductsManagingMenu getInstance() {
        if (instance == null) {
            instance = new SellerProductsManagingMenu();
        }
        return instance;
    }

}
