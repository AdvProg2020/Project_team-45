package view.nedaei.buyermenu;

import controller.Controller;
import controller.UserController;
import view.bagheri.Panel;
import view.hatami.ManagingMenu;

public class OrdersManagingMenu extends ManagingMenu {
    private static OrdersManagingMenu instance;

    public OrdersManagingMenu() {
        super("order managing panel");
        this.submenus.put("show order (\\w+)", createShowOrderByIdPanel());
        this.submenus.put("rate (\\w+) ([1-5])", createRateProductByIdPanel());
    }

    public static OrdersManagingMenu getInstance() {
        if (instance == null) {
            instance = new OrdersManagingMenu();
        }
        return instance;
    }

    private Panel createShowOrderByIdPanel() {
        return new Panel("show order by id panel") {

            @Override
            public void execute() {
                String buyLogDisplay = UserController.getInstance().getBuyerBuyLogDisplayById(matcher.group(1));
                System.out.println(buyLogDisplay == null? "id not found!" : buyLogDisplay);
            }
        };
    }

    private Panel createRateProductByIdPanel() {
        return new Panel("rate product by id panel") {

            @Override
            public void execute() {
                if (!UserController.getInstance().didBuyerBuyProduct(matcher.group(1))) {
                    System.out.println("is not among your orders");
                    return;
                }
                UserController.getInstance().rateProductById(matcher.group(1), Integer.parseInt(matcher.group(2)));
            }

        };
    }

    @Override
    protected void showHelp() {
        System.out.println("");
    }

    @Override
    protected void show() {
        System.out.println(UserController.getInstance().getBuyerBuyLogs());
    }

}
