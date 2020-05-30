package view.userMenus.sellerMenus.productsmanagingmenu;

import controller.InputValidator;
import controller.userControllers.SellerController;
import controller.userControllers.UserController;
import model.user.Seller;
import view.Panel;

import java.util.HashMap;
import java.util.LinkedHashMap;

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
        LinkedHashMap<String, InputValidator> productFields;
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        if (mode.equals("existing")) {
            productFields = SellerController.getInstance().getExistingProductFieldsToCreate();
            String productId;
            InputValidator validator = InputValidator.getExistingProductValidator();
            System.out.println("product Id: (" + validator.getFormatToShow() + ")");
            while (!validator.checkInput(productId = scanner.nextLine())){
                System.out.println("invalid format or product not found");
                fieldsAndValues.put("product Id", productId);
            }
        } else {
            productFields = SellerController.getInstance().getNewProductFieldsToCreate();
        }
        for (String productField : productFields.keySet()) {
            InputValidator validator = productFields.get(productField);
            System.out.println(productField + ": (" + validator.getFormatToShow() + ")");
            String input;
            while (!validator.checkInput(input = scanner.nextLine())){
                System.out.println("invalid format");
            }
            fieldsAndValues.put(productField, input.trim());
        }
        SellerController.getInstance().createAddProductRequest(mode, ((Seller) UserController.getActiveUser())
                , fieldsAndValues);
    }

    @Override
    protected void show() {
        String input;
        while (true) {
            System.out.println("select mode;\n" +
                    "1 for existing products, 2 for new product:");
            if ((input = scanner.nextLine().trim()).equals("1")) {
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
