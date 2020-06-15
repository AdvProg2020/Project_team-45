package newViewNedaei.user.buyer;

import javafx.scene.input.KeyEvent;
import newViewHatami.Validator;

public class CartManagingMenu {

    public static String getFxmlFilePath() {
        return "/CartManagingMenu.fxml";
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }
}
