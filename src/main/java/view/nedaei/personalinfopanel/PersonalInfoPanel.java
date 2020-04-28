package view.nedaei.personalinfopanel;

import controller.Controller;
import view.Panel;

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
        while (!(input = scanner.nextLine()).equals("back")) {
            if ((matcher = PersonalInfoPanelCommands.EDIT.getMatcher(input)).find()) {
                System.out.print("please enter new value: ");
                Controller.getInstance().setPersonalInfoField(matcher.group(3), scanner.nextLine().trim());
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    @Override
    protected void show() {
        System.out.println(Controller.getInstance().getPersonalInfoDisplay());
    }

}

