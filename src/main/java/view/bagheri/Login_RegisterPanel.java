package view.bagheri;

import controller.userControllers.UserController;
import view.hatami.RegisterPanel;

public class Login_RegisterPanel extends Panel {
    private static final Login_RegisterPanel instance = new Login_RegisterPanel();
    RegisterPanel registerPanel;
    UserController userController;

    private Login_RegisterPanel() {
        super("Login/Register Panel");
        registerPanel = RegisterPanel.getInstance();
        userController = UserController.getInstance();
    }

    public static Login_RegisterPanel getInstance() {
        return instance;
    }

    @Override
    public void execute() {
        if (UserController.isLoggedIn()) {
            System.out.println("You have already logged in");
            return;
        }
        String inputCommand;
        while (!(inputCommand = scanner.nextLine()).equals("back")) {
            if ((matcher = getMatcher("login (\\w+)", inputCommand)) != null) {
                if(login())
                    return;
            } else if((matcher = getMatcher("create account (buyer|seller) (\\w+)", inputCommand)) != null) {
                registerPanel.execute();
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private boolean login() {
        if (!userController.setActiveUserByUsername(matcher.group(1))) {
            System.out.println("The username is invalid!");
            return false;
        }
        String input;
        System.out.println("Please enter your password.");
        while (!(input = scanner.nextLine()).equals("back")) {
            if (userController.login(input)) {
                return true;
            } else {
                System.out.println("The password is invalid!");
            }
        }
        return false;
    }
}