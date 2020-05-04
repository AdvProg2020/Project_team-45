package view.nedaei.buyermenu;

import controller.Controller;
import view.bagheri.Panel;
import view.hatami.ManagingMenu;

public class OrdersManagingMenu extends ManagingMenu {
    private static OrdersManagingMenu instance;

    public OrdersManagingMenu() {
        super("order managing panel", null);
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
            protected void execute() {
                System.out.println(Controller.getInstance().getBuyerBuyLogById(matcher.group(1)));
            }
        };
    }

    private Panel createRateProductByIdPanel() {
        return new Panel("rate product by id panel") {

            @Override
            protected void execute() {
                if (!Controller.getInstance().didBuyerBuyProduct(matcher.group(1))) {
                    System.out.println("is not among your orders");
                    return;
                }
                Controller.getInstance().rateProductById(matcher.group(1), Integer.parseInt(matcher.group(2)));
            }

        };
    }

    @Override
    protected void showHelp() {
        System.out.println("");
    }

    @Override
    protected void show() {
        System.out.println(Controller.getInstance().getBuyerBuyLogs());
    }

}
