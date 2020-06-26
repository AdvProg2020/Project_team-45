package newViewNedaei.user.seller.product;

import controller.ProductController;
import controller.userControllers.SellerController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import model.Market;
import model.category.FinalCategory;
import model.product.Product;
import newViewHatami.Validator;
import newViewHatami.ValidatorField;
import newViewNedaei.MenuController;
import newViewNedaei.Panel;

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

    private String productPhotoPath;
    private String productVideoPath;

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
//        HashMap<String, String> fieldsAndValues = new HashMap<>();
//        fieldsAndValues.put("productId", id.getText());
//        fieldsAndValues.put("price", existingPrice.getText());
//        fieldsAndValues.put("stock", existingStock.getText());
        Product product = ProductController.getInstance().getItemById(id.getText());
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
        } if (Market.getInstance().getCategoryByName(category.getText()) == null) {
            newError.setText("category does not exist");
            return;
        } if (!Market.getInstance().getCategoryByName(category.getText()).getType().equals("FinalCategory")) {
            newError.setText("category is not final");
            return;
        } if (!newPrice.validate()) {
            newError.setText("invalid price format");
            return;
        } if (!newStock.validate()) {
            newError.setText("invalid stock format");
            return;
        }
//        HashMap<String, String> fieldsAndValues = new HashMap<>();
//        fieldsAndValues.put("name", name.getText());
//        fieldsAndValues.put("categoryName", category.getText());
//        fieldsAndValues.put("description", description.getText());
//        fieldsAndValues.put("price", newPrice.getText());
//        fieldsAndValues.put("stock", newStock.getText());
        Product product = new Product(name.getText(), (FinalCategory) Market.getInstance().getCategoryByName(category.getText()), description.getText(), productPhotoPath, productVideoPath);
        SellerController.getInstance().createAddProductRequest("new", product, Integer.parseInt(newPrice.getText()), Integer.parseInt(newStock.getText()));
        existingError.setText("");
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
}
