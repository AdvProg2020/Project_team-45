package view.nedaei.buyermenu;

import controller.Controller;
import view.bagheri.Panel;
import view.bagheri.ProductMenu;
import view.hatami.ManagingMenu;

public class CartManagingMenu extends ManagingMenu {
    private static CartManagingMenu instance;

    private CartManagingMenu() {
        super("cart managing page", null);
        this.submenus.put("show products", createShowProductsPanel());
        this.submenus.put("view (\\w+)", ProductMenu.getInstance()); // bagheri should use the id in matcher
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

    private Panel createShowProductsPanel() {
        return new Panel("show products panel") {

            @Override
            protected void execute() {
                System.out.println(Controller.getInstance().getCartProductsList());
            }

        };
    }

    private Panel createIncreaseProductByIdPanel() {
        return new Panel("increase product by id panel") {

            @Override
            protected void execute() {
                Controller.getInstance().increaseCartProductById(matcher.group(1));
            }

        };
    }

    private Panel createDecreaseProductByIdPanel() {
        return new Panel("decrease product by id panel") {

            @Override
            protected void execute() {
                Controller.getInstance().decreaseCartProductById(matcher.group(1));
            }

        };
    }

    private Panel createShowTotalPricePanel() {
        return new Panel("show total price panel") {

            @Override
            protected void execute() {
                System.out.println(Controller.getInstance().getCartTotalPrice());
            }

        };
    }

    @Override
    protected void showHelp() {
        System.out.println("");
    }

    @Override
    protected void show() {
        System.out.println(Controller.getInstance().getCartProductsList());
    }
}
