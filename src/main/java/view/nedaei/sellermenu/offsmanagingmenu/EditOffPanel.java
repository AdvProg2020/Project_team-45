package view.nedaei.sellermenu.offsmanagingmenu;

import controller.Controller;
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
        Controller.getInstance().createOffEditionRequest(this.matcher.group(1), fieldsAndValues);
    }

    @Override
    protected void show() {
        System.out.println("[field to edit] : [field's new value]\n" +
                        "off available fields to edit are:\n"
                Controller.getInstance().getOffAvailableFieldsToEdit());
    }

}
