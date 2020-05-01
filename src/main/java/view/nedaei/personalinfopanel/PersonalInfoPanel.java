package view.nedaei.personalinfopanel;

import controller.Controller;
import view.bagheri.Panel;

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
            if ((matcher = PersonalInfoPanelCommands.EDIT.getMatcher(input)).find()) {
                System.out.println("please enter new value: ");
                Controller.getInstance().setPersonalInfoField(matcher.group(1), scanner.nextLine().trim());
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    @Override
    protected void show() {
        System.out.println(Controller.getInstance().getPersonalInfo());
    }

}

