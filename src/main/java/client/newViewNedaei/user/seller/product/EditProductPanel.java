package client.newViewNedaei.user.seller.product;

import client.controller.OffController;
import client.controller.ProductController;
import client.controller.userControllers.SellerController;
import client.newViewHatami.Validator;
import client.newViewHatami.ValidatorField;
import client.newViewNedaei.Panel;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;

public class EditProductPanel extends Panel {
    public Label error;
    public ValidatorField price;
    public ValidatorField stock;
    public TextField offId;

    public static String getFxmlFilePath() {
        return "/EditProductPanel.fxml";
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    public void sendRequest() {
        if (!offId.getText().equals("") && !OffController.getInstance().offIdExists(offId.getText())) {
            error.setText("off id does not exist");
            return;
        } if (!price.getText().equals("") && !price.validate()) {
            error.setText("invalid price format");
            return;
        } if (!stock.getText().equals("") && !stock.validate()) {
            error.setText("invalid stock format");
            return;
        }
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("offId", offId.getText());
        fieldsAndValues.put("price", price.getText());
        fieldsAndValues.put("stock", stock.getText());
        SellerController.getInstance().createProductEditionRequest(ProductController.getInstance()
                .getActiveProductSellInfo().getProduct().getId(), fieldsAndValues);
        error.setText("");
    }
}
