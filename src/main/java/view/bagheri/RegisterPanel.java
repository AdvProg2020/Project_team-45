package view.bagheri;

public class RegisterPanel extends Panel {
    private static final RegisterPanel instance = new RegisterPanel();


    private RegisterPanel() {
        super("Register Panel");
    }

    public static RegisterPanel getInstance() {
        return instance;
    }

    public void execute() {

    }
}
