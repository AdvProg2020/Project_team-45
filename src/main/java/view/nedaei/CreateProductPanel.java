package view.nedaei;

import view.bagheri.Panel;

public class CreateProductPanel extends Panel {
    private static CreateProductPanel instance;

    private CreateProductPanel() {
        super("create product panel");
    }

    public static CreateProductPanel getInstance() {
        if (instance == null) {
            instance = new CreateProductPanel();
        }
        return instance;
    }

}
