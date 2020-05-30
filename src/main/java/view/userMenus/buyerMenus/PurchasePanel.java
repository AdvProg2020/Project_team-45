package view.userMenus.buyerMenus;

import controller.userControllers.BuyerController;
import controller.userControllers.UserController;
import view.Login_RegisterPanel;
import view.Panel;

import java.util.ArrayList;
import java.util.HashMap;

public class PurchasePanel extends Panel {
    private static PurchasePanel instance;

    private PurchasePanel() {
        super("purchase panel");
    }

    public static PurchasePanel getInstance() {
        if (instance == null) {
            instance = new PurchasePanel();
        }
        return instance;
    }

    @Override
    public void execute() {
        if (!(UserController.isLoggedIn() && UserController.getActiveUser().getRole().equals("buyer"))) {
            Login_RegisterPanel.getInstance().execute();
            if (!(UserController.isLoggedIn() && UserController.getActiveUser().getRole().equals("buyer"))) {
                return;
            }
        }
        runReceiverInformationPanel();
        runDiscountCodePanel();
        runPaymentPanel();
    }

    private void runReceiverInformationPanel() {
        new Panel("receiver information panel") {

            @Override
            public void execute() {
                ArrayList<String> buyLogInformationToReceive = BuyerController.getInstance()
                        .getBuyLogInformationToReceive();
                HashMap<String, String> fieldsAndValues = new HashMap<>();
                for (String field : buyLogInformationToReceive) {
                    System.out.println(field + ":");
                    fieldsAndValues.put(field, scanner.nextLine().trim());
                }
                BuyerController.getInstance().createNewLog(fieldsAndValues);
            }

        }.execute();
    }

    private void runDiscountCodePanel() {
        new Panel("discount code panel") {

            @Override
            public void execute() {
                System.out.println("would you like to use any discount codes?");
                String input;
                while (!(input = scanner.nextLine().trim()).equalsIgnoreCase("no")) {
                    if (input.equalsIgnoreCase("yes")) {
                        System.out.println("discount code:");
                        if (BuyerController.getInstance().isDiscountCodeValid(input = scanner.nextLine().trim())) {
                            BuyerController.getInstance().applyDiscountCode(input);
                        } else {
                            System.out.println("invalid discount code!");
                        }
                        return;
                    } else {
                        System.out.println("invalid command!");
                    }
                }
            }

        }.execute();
    }

    private void runPaymentPanel() {
        new Panel("payment panel") {

            @Override
            public void execute() {
                if (!BuyerController.getInstance().canPurchase()) {
                    System.out.println("you do not have enough balance!");
                    return;
                }
                BuyerController.getInstance().purchase();
            }

        }.execute();
    }

}
