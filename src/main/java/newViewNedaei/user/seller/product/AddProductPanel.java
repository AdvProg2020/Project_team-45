package newViewNedaei.user.seller.product;

import controller.ProductController;
import controller.userControllers.SellerController;
import controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Market;
import model.user.Seller;
import newViewHatami.Validator;
import newViewHatami.ValidatorField;
import newViewNedaei.MenuController;
import newViewNedaei.Panel;

import java.util.HashMap;

public class AddProductPanel extends Panel {
    public TextField id;
    public ValidatorField existingPrice;
    public ValidatorField existingStock;
    public Label existingError;
    public ValidatorField name;
    public ValidatorField category;
    public TextField description;
    public ValidatorField newPrice;
    public ValidatorField newStock;
    public Label newError;
    @FXML
    private GridPane newPane;
    @FXML
    private GridPane existingPane;
    @FXML
    private CheckBox checkBox;

    public static String getFxmlFilePath() {
        return "/AddProductPanel.fxml";
    }

    @FXML
    public void initialize() {
        existingPane.setDisable(true);
        existingPane.setVisible(false);
        newPane.setDisable(false);
        newPane.setVisible(true);
    }

    public void actionCheckBox() {
        if (checkBox.isSelected()) {
            newPane.setDisable(true);
            newPane.setVisible(false);
            existingPane.setDisable(false);
            existingPane.setVisible(true);
        } else {
            existingPane.setDisable(true);
            existingPane.setVisible(false);
            newPane.setDisable(false);
            newPane.setVisible(true);
        }
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    public void sendExisting() {
        if (ProductController.getInstance().getItemById(id.getText()) == null) {
            existingError.setText("product does not exist");
            return;
        } if (!existingPrice.validate()) {
            existingError.setText("invalid price format");
            return;
        } if (!existingStock.validate()) {
            existingError.setText("invalid stock format");
            return;
        }
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("productId", id.getText());
        fieldsAndValues.put("price", existingPrice.getText());
        fieldsAndValues.put("stock", existingStock.getText());
        SellerController.getInstance().createAddProductRequest("existing", (Seller) UserController.getActiveUser(), fieldsAndValues);
        existingError.setText("");
    }

    public void sendNew() {
        if (!name.validate()) {
            newError.setText("invalid product name format");
            return;
        } if (!category.validate()) {
            newError.setText("invalid category name format");
            return;
        } if (!newPrice.validate()) {
            newError.setText("invalid price format");
            return;
        } if (!newStock.validate()) {
            newError.setText("invalid stock format");
            return;
        }
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("name", name.getText());
        fieldsAndValues.put("categoryName", category.getText());
        fieldsAndValues.put("description", description.getText());
        fieldsAndValues.put("price", newPrice.getText());
        fieldsAndValues.put("stock", newStock.getText());
        SellerController.getInstance().createAddProductRequest("new", (Seller) UserController.getActiveUser(), fieldsAndValues);
        existingError.setText("");
    }
}
