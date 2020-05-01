package view.nedaei.sellermenu.productsmanagingmenu;

import controller.Controller;
import view.bagheri.Panel;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateProductPanel extends Panel {
    private static CreateProductPanel instance;

    private CreateProductPanel() {
        super("create product panel");
    }

    public static CreateProductPanel getInstance() {
        if (instance == null) {
            instance = new CreateProductPanel();
        }
        return instance;
    }

    @Override
    protected void execute() {
        ArrayList<String> productFields = Controller.getInstance().getProductFieldsToCreate());
        HashMap<String, String> fieldsAndValues = new HashMap<String, String>();
        for (String productField : productFields) {
            System.out.println(productField + ":");
            fieldsAndValues.put(productField, scanner.nextLine().trim());
        }
        Controller.getInstance().createAddProductRequest(fieldsAndValues);
    }

}
