package view.bagheri;

import controller.UserController;

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
        if (userController.isUserLoggedIn) {
            System.out.println();
            return;
        }
        String inputCommand;
        while (!(inputCommand = scanner.nextLine()).equals("back")) {
            if ((matcher = getMatcher("login (username)", inputCommand)) != null) {
                login();
            } else if((matcher = getMatcher("create account (type) (username)", inputCommand)) != null) {
                registerPanel.execute();
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private void login() {
        userController.setActiveUser();
        String input;
        while (!(input = scanner.nextLine()).equals("back")) {
            if (userController.checkPassword(input)) {
                System.out.println("invalid command!");
            } else {
                userController.setActiveUser();
            }
        }
    }
}
