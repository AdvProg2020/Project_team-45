package view.nedaei.sellermenu.offsmanagingmenu;

import controller.SellerController;
import controller.UserController;
import view.bagheri.Panel;
import view.nedaei.sellermenu.EditPanelsCommands;

import java.util.HashMap;
import java.util.regex.Matcher;

public class EditOffPanel extends Panel {
    private static EditOffPanel instance;

    private EditOffPanel() {
        super("edit off panel");
    }

    public static EditOffPanel getInstance() {
        if (instance == null) {
            instance = new EditOffPanel();
        }
        return instance;
    }

    @Override
    public void execute() {
        show();
        String input;
        Matcher matcher;
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        while (!(input = scanner.nextLine().trim()).equalsIgnoreCase("done")) {
            if ((matcher = EditPanelsCommands.FIELD_AND_VALUE.getMatcher(input)).find() &&
                    SellerController.getInstance().getOffAvailableFieldsToEdit().contains(matcher.group(1))) {
                fieldsAndValues.put(matcher.group(1), matcher.group(2));
            } else {
                System.out.println("invalid command!");
            }
        }
        SellerController.getInstance().createOffEditionRequest(this.matcher.group(1), fieldsAndValues);
    }

    @Override
    protected void show() {
        System.out.println("[field to edit] : [field's new value] (dates should be in dd/M/yyyy format)\n" +
                "'done' when done!\n" +
                "off available fields to edit are:\n" +
                SellerController.getInstance().getOffAvailableFieldsToEdit());
    }

}
