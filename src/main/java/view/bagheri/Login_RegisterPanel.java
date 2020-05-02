package view.bagheri;

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

    }
}
