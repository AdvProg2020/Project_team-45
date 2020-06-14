package newViewNedaei.user.seller.product;

import controller.userControllers.SellerController;
import controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import model.product.Product;
import model.user.Seller;
import newViewNedaei.MenuController;
import newViewNedaei.Panel;

import java.util.Set;

public class RemoveProductPanel extends Panel {
    public ChoiceBox<String> targetProduct;
    public Label error;

    public static String getFxmlFilePath() {
        return "/RemoveProductPanel.fxml";
    }

    @FXML
    public void initialize() {
        Set<Product> availableProducts =  ((Seller) UserController.getActiveUser()).getAvailableProducts().keySet();
        for (Product product : availableProducts) {
            targetProduct.getItems().add(product.getName() + " -> " + product.getId());
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
