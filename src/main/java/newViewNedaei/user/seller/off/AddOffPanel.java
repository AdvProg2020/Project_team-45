package newViewNedaei.user.seller.off;

import controller.userControllers.SellerController;
import controller.userControllers.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.product.Product;
import model.user.Seller;
import newViewHatami.Validator;
import newViewHatami.ValidatorField;
import newViewNedaei.Panel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AddOffPanel extends Panel {
    public ChoiceBox<String> products;
    public ListView<String> productsList;
    public TextField startDate;
    public TextField endDate;
    public ValidatorField discount;
    public Label error;

    public static String getFxmlFilePath() {
        return "/AddOffPanel.fxml";
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    @FXML
    public void initialize() {
        Set<Product> availableProducts =  ((Seller) UserController.getActiveUser()).getAvailableProducts().keySet();
        for (Product product : availableProducts) {
            products.getItems().add(product.getName() + " -> " + product.getId());
        }
    }

    public void addToProducts() {
        String id = products.getValue();
        if (!productsList.getItems().contains(id)) {
            productsList.getItems().add(id);
        }
    }

    public void sendRequest() {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            simpleDateFormat.parse(startDate.getText());
            fieldsAndValues.put("startDate", startDate.getText());
        } catch (ParseException parseException) {
            error.setText("invalid start date format");
            return;
        }
        try {
            simpleDateFormat.parse(endDate.getText());
            fieldsAndValues.put("endDate", endDate.getText());
        } catch (ParseException parseException) {
            error.setText("invalid end date format");
            return;
        }
        if (!discount.validate()) {
            error.setText("invalid discount amount format");
            return;
        }
        fieldsAndValues.put("discountAmount", discount.getText());
        String productsString = "";
        for (String item : productsList.getItems()) {
            productsString += item + ", ";
        }
        fieldsAndValues.put("productIds(separated by ',')", productsString);
        SellerController.getInstance().createAddOffRequest(fieldsAndValues);
        error.setText("");
    }

}
