package client.newViewNedaei.user.seller.product;

import client.controller.CategoryController;
import client.controller.ProductController;
import client.controller.userControllers.SellerController;
import client.newViewHatami.Validator;
import client.newViewHatami.ValidatorField;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

// nedaei: turned to new format successfully!
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
    public GridPane newPane;
    public GridPane existingPane;
    public CheckBox checkBox;
    public CheckBox isFile;
    public Button fileBrowse;

    private String productPhotoPath;
    private String productVideoPath;
    private String productFilePath;

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
        if (!ProductController.getInstance().hasProductWithId(id.getText())) {
            existingError.setText("product does not exist");
            return;
        } if (!existingPrice.validate()) {
            existingError.setText("invalid price format");
            return;
        } if (!existingStock.validate()) {
            existingError.setText("invalid stock format");
            return;
        }
        HashMap<String, String> product = new HashMap<>();
        product.put("id", id.getText());
        product.put("price", existingPrice.getText());
        product.put("stock", existingStock.getText());
        SellerController.getInstance().createAddProductRequest("existing", product, Integer.parseInt(existingPrice.getText()), Integer.parseInt(existingStock.getText()));
        existingError.setText("");
    }

    public void sendNew() {
        if (!name.validate()) {
            newError.setText("invalid product name format");
            return;
        } if (!category.validate()) {
            newError.setText("invalid category name format");
            return;
        } if (CategoryController.getInstance().getCategoryId(name.getText()) == null) {
            newError.setText("category does not exist");
            return;
        } if (CategoryController.getInstance().getCategoryTypeByName(name.getText()).equals("FinalCategory")) {
            newError.setText("category is not final");
            return;
        } if (!newPrice.validate()) {
            newError.setText("invalid price format");
            return;
        } if (!newStock.validate()) {
            newError.setText("invalid stock format");
            return;
        }
        HashMap<String, String> product = new HashMap<>();
        product.put("name", name.getText());
        product.put("categoryName", category.getText());
        product.put("description", description.getText());
        product.put("price", newPrice.getText());
        product.put("stock", newStock.getText());
        if (isFile.isSelected()) {
            product.put("isFile", "true");
            product.put("filePath", productFilePath);
        } else {
            product.put("isFile", "false");
        }
        SellerController.getInstance().createAddProductRequest("new", product, Integer.parseInt(newPrice.getText()), Integer.parseInt(newStock.getText()));
        newError.setText("");
    }

    public void pickPhoto() {
        String photoPath = MenuController.getInstance().pickPhoto();
        if (photoPath != null) {
            productPhotoPath = photoPath;
        }
    }

    public void pickVideo() {
        String videoPath = MenuController.getInstance().pickVideo();
        if (videoPath != null) {
            productVideoPath = videoPath;
        }
    }

    public void checkFileCheckBox(ActionEvent event) {
        if (isFile.isSelected()) {
            fileBrowse.setDisable(false);
        } else {
            fileBrowse.setDisable(true);
        }
    }

    public void pickFile(MouseEvent mouseEvent) {
        String filePath = MenuController.getInstance().pickPhoto();
        if (filePath != null) {
            productFilePath = filePath;
        }
    }
}
