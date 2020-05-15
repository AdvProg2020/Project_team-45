package view.nedaei.sellermenu.productsmanagingmenu;

import controller.UserController;
import view.bagheri.Panel;

import java.util.ArrayList;
import java.util.HashMap;

public class AddProductPanel extends Panel {
    private static AddProductPanel instance;
    private String mode;

    private AddProductPanel() {
        super("create product panel");
    }

    public static AddProductPanel getInstance() {
        if (instance == null) {
            instance = new AddProductPanel();
        }
        return instance;
    }

    @Override
    public void execute() {
        show();
        ArrayList<String> productFields;
        if (mode.equals("existing")) {
            productFields = UserController.getInstance().getExistingProductFieldsToCreate();
        } else {
            productFields = UserController.getInstance().getNewProductFieldsToCreate();
        }
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        for (String productField : productFields) {
            System.out.println(productField + ":");
            fieldsAndValues.put(productField, scanner.nextLine().trim());
        }
        UserController.getInstance().createAddProductRequest(fieldsAndValues);
    }

    @Override
    protected void show() {
        String input;
        while (true) {
            System.out.println("select mode;\n" +
                    "1 for existing products, 2 for new product:");
            if((input = scanner.nextLine().trim()).equals("1")) {
                mode = "existing";
                break;
            } else if (input.equals("2")) {
                mode = "new";
                break;
            } else {
                System.out.println("invalid command!");
            }
        }
    }

}
