package view.nedaei.buyermenu;

import controller.userControllers.BuyerController;
import view.bagheri.Panel;
import view.bagheri.ProductMenu;
import view.hatami.ManagingMenu;

public class CartManagingMenu extends ManagingMenu {
    private static CartManagingMenu instance;

    private CartManagingMenu() {
        super("cart managing page");
        this.submenus.put("show products", createShowProductsPanel());
        this.submenus.put("view (\\w+)", ProductMenu.getInstance());
        this.submenus.put("increase (\\w+)", createIncreaseProductByIdPanel());
        this.submenus.put("decrease (\\w+)", createDecreaseProductByIdPanel());
        this.submenus.put("show total price", createShowTotalPricePanel());
        this.submenus.put("purchase", PurchasePanel.getInstance());
    }

    public static CartManagingMenu getInstance() {
        if (instance == null) {
            instance = new CartManagingMenu();
        }
        return instance;
    }

    @Override
    protected void show() {
        System.out.println(name);
    }

    private Panel createShowProductsPanel() {
        return new Panel("show products panel") {

            @Override
            public void execute() {
                System.out.println(BuyerController.getInstance().getCartProductsList());
            }

        };
    }

    private Panel createIncreaseProductByIdPanel() {
        return new Panel("increase product by id panel") {

            @Override
            public void execute() {
                BuyerController.getInstance().increaseCartProductById(matcher.group(1));
            }

        };
    }

    private Panel createDecreaseProductByIdPanel() {
        return new Panel("decrease product by id panel") {

            @Override
            public void execute() {
                BuyerController.getInstance().decreaseCartProductById(matcher.group(1));
            }

        };
    }

    private Panel createShowTotalPricePanel() {
        return new Panel("show total price panel") {

            @Override
            public void execute() {
                System.out.println(BuyerController.getInstance().getCartTotalPrice());
            }

        };
    }

    @Override
    protected void showHelp() {
        super.showHelp();
        System.out.println();
    }

//    @Override
//    protected void show() {
//        System.out.println(UserController.getInstance().getCartProductsList());
//    }

}
