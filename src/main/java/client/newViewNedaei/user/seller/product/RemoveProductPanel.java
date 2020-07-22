package client.newViewNedaei.user.seller.product;

import client.controller.userControllers.SellerController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashMap;

// nedaei: turned to new format successfully!
public class RemoveProductPanel extends Panel {
    public ChoiceBox<String> targetProduct;
    public Label error;

    public static String getFxmlFilePath() {
        return "/RemoveProductPanel.fxml";
    }

    @FXML
    public void initialize() {
        ArrayList<HashMap<String, String>> availableProducts = SellerController.getInstance().getAvailableProducts();
        for (HashMap<String, String> product : availableProducts) {
            targetProduct.getItems().add(product.get("name") + " -> " + product.get("id"));
        }
    }

    public void sendRequest() {
        if (targetProduct.getValue() == null) {
            error.setText("choose an id");
            return;
        }
        SellerController.getInstance().createRemoveProductRequest(targetProduct.getValue().substring(targetProduct.getValue().indexOf(" -> ") + 4));
        error.setText("");
    }
}
