package view.nedaei.sellermenu.offsmanagingmenu;

import controller.Controller;
import view.bagheri.Panel;

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
    protected void execute() {
        ArrayList<String> productFields = Controller.getInstance().getOffFieldsToCreate());
        HashMap<String, String> fieldsAndValues = new HashMap<String, String>();
        for (String productField : productFields) {
            System.out.println(productField + ":");
            fieldsAndValues.put(productField, scanner.nextLine().trim());
        }
        Controller.getInstance().createAddOffRequest(fieldsAndValues);
    }

}
