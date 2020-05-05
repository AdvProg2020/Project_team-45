package view.bagheri;

import controller.Controller;

public class Login_RegisterPanel extends Panel {
    private static Login_RegisterPanel instance = new Login_RegisterPanel();


    private Login_RegisterPanel() {
        super("Login/Register Panel");
    }

    public static Login_RegisterPanel getInstance() {
        return instance;
    }

    @Override
    protected void execute() {
        if (Controller.getInstance().isUserLoggedIn) {
            System.out.println("");
            return;
        }
        String inputCommand;
        while (!(inputCommand = scanner.nextLine()).equals("back")) {
            if ((matcher = getMatcher("login (username)", inputCommand)) != null) {
                login();
            } else if((matcher = getMatcher("create account (type) (username)", inputCommand)) != null) {

            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private void login() {

        while (!(inputCommand = scanner.nextLine()).equals("back")) {
            if (Controller.getInstance().checkPassword(inputCommand)) {
                System.out.println("invalid command!");
            } else {
                Controller.getInstance().setActiveUser();
            }
        }
    }
}
