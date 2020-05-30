package view.userMenus.sellerMenus.productsmanagingmenu;

import controller.userControllers.SellerController;
import view.Panel;
import view.UIPage;
import view.userMenus.sellerMenus.EditPanelsCommands;

import java.util.HashMap;
import java.util.regex.Matcher;

public class EditProductPanel extends Panel {
    private static EditProductPanel instance;

    private EditProductPanel() {
        super("edit product panel");
    }

    public static EditProductPanel getInstance() {
        if (instance == null) {
            instance = new EditProductPanel();
        }
        return instance;
    }

    @Override
    public void execute() {
        if (SellerController.getInstance().getSellerAvailableProductById(matcher.group(1)) == null) {
            System.out.println("id not found!");
            return;
        }
        show();
        String input;
        Matcher matcher;
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        while (!(input = scanner.nextLine().trim()).equalsIgnoreCase("done")) {
            if ((matcher = EditPanelsCommands.FIELD_AND_VALUE.getMatcher(input)).find()) {
                if (SellerController.getInstance().isProductFieldAvailableToEdit(UIPage.matcher.group(1)
                        , matcher.group(1))) {
                    fieldsAndValues.put(matcher.group(1), matcher.group(2));
                } else {
                    System.out.println("invalid field to edit!");
                }
            } else {
                System.out.println("invalid command!");
            }
        }
        SellerController.getInstance().createProductEditionRequest(UIPage.matcher.group(1), fieldsAndValues);
    }

    @Override
    protected void show() {
        System.out.println("[field to edit] : [field's new value]\n" +
                "'done' when done!\n" +
                "product available fields to edit are:\n" +
                SellerController.getInstance().getProductAvailableFieldsToEditDisplay());
    }

}
