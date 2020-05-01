package view.nedaei.buyermenu;

import controller.Controller;
import view.bagheri.Login_RegisterPanel;
import view.bagheri.Panel;

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
    protected void execute() {
        if (!Controller.getInstance().isLoggedIn()) {
            Login_RegisterPanel.getInstance().execute();
        }
        runReceiverInformationPanel();
        runDiscountCodePanel();
        runPaymentPanel();
    }

    private void runReceiverInformationPanel() {
        new Panel("receiver information panel") {

            @Override
            public void execute() {
                ArrayList<String> buyLogInformationToReceive = Controller.getInstance().getBuyLogInformationToReceive());
                HashMap<String, String> fieldsAndValues = new HashMap<String, String>();
                for (String field : buyLogInformationToReceive) {
                    System.out.println(field + ":");
                    fieldsAndValues.put(field, scanner.nextLine().trim());
                }
                Controller.getInstance().createBuyLog(fieldsAndValues);
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
                        if (Controller.getInstance().isDiscountCodeValid(input = scanner.nextLine().trim())) {
                            Controller.getInstance().applyDiscountCode(input);
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
            protected void execute() {
                if (!Controller.getInstance().canPurchase()) {
                    System.out.println("you do not have enough balance!");
                    return;
                }
                Controller.getInstance().purchase();
            }

        }.execute();
    }

}
