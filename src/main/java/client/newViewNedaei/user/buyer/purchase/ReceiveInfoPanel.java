package client.newViewNedaei.user.buyer.purchase;

import client.controller.userControllers.BuyerController;
import client.newViewHatami.Validator;
import client.newViewHatami.ValidatorField;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;

// nedaei: turned to new format successfully!
public class ReceiveInfoPanel extends Panel {
    public ValidatorField phone;
    public TextField address;
    public Label error;

    public static String getFxmlFilePath() {
        return "/ReceiveInfoPanel.fxml";
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    public void goNext() {
        if (!phone.validate()) {
            error.setText("invalid phone number format");
            return;
        }
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("address", address.getText());
        fieldsAndValues.put("phoneNumber", phone.getText());
        BuyerController.getInstance().createNewLog(fieldsAndValues);
        error.setText("");

        MenuController.getInstance().removeCurrentPanel();
        MenuController.getInstance().goToPanel(DiscountCodePanel.getFxmlFilePath());
    }
}
