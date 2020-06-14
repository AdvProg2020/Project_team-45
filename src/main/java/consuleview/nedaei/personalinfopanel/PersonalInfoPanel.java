package consuleview.nedaei.personalinfopanel;

import controller.userControllers.UserController;
import consuleview.Panel;

import java.util.regex.Matcher;

public class PersonalInfoPanel extends Panel {
    private static PersonalInfoPanel instance;

    private PersonalInfoPanel() {
        super("personal info panel");
    }

    public static PersonalInfoPanel getInstance() {
        if (instance == null) {
            instance = new PersonalInfoPanel();
        }
        return instance;
    }

    @Override
    public void execute() {
        show();
        String input;
        Matcher matcher;
        while (!(input = scanner.nextLine().trim()).equalsIgnoreCase("back")) {
            if ((matcher = PersonalInfoPanelCommands.EDIT.getMatcher(input)).find() &&
                    UserController.getPersonalInfoFieldsToEdit().contains(matcher.group(1))) {
                System.out.print("new value: ");
                try {
                    UserController.getInstance().setPersonalInfoField(matcher.group(1), scanner.nextLine().trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    @Override
    protected void show() {
        super.show();
        System.out.println(UserController.getInstance().getPersonalInfoDisplay());
    }

}

