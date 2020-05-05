package view.nedaei.sellermenu.productsmanagingmenu;

import controller.Controller;
import controller.UserController;
import view.bagheri.Panel;
import view.nedaei.sellermenu.EditPanelsCommands;

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
    protected void execute() {
        show();
        String input;
        Matcher matcher;
        HashMap<String, String> fieldsAndValues = new HashMap<String, String>();
        while (!(input = scanner.nextLine().trim()).equalsIgnoreCase("done")) {
            if ((matcher = EditPanelsCommands.FIELD_AND_VALUE.getMatcher(input)).find()) {
                fieldsAndValues.put(matcher.group(1), matcher.group(2));
            } else {
                System.out.println("invalid command!");
            }
        }
        UserController.getInstance().createProductEditionRequest(this.matcher.group(1), fieldsAndValues);
    }

    @Override
    protected void show() {
        System.out.println("[field to edit] : [field's new value]\n" +
                "product available fields to edit are:\n" +
                UserController.getInstance().getProductAvailableFieldsToEditDisplay());
    }

}
