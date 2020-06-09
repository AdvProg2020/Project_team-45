package view.nedaei.sellerMenus.offsmanagingmenu;

import controller.userControllers.SellerController;
import view.Panel;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateOffPanel extends Panel {
    private static CreateOffPanel instance;

    private CreateOffPanel() {
        super("create off panel");
    }

    public static CreateOffPanel getInstance() {
        if (instance == null) {
            instance = new CreateOffPanel();
        }
        return instance;
    }

    @Override
    public void execute() {
        ArrayList<String> productFields = SellerController.getInstance().getOffFieldsToCreate();
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        for (String productField : productFields) {
            System.out.println(productField + ":");
            fieldsAndValues.put(productField, scanner.nextLine().trim());
        }
        SellerController.getInstance().createAddOffRequest(fieldsAndValues);
    }

}
