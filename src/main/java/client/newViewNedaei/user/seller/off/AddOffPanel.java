package client.newViewNedaei.user.seller.off;

import client.controller.userControllers.SellerController;
import client.newViewHatami.Validator;
import client.newViewHatami.ValidatorField;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

// nedaei: turned to new format successfully!
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
        ArrayList<HashMap<String, String>> availableProducts =  SellerController.getInstance().getAvailableProducts();
        for (HashMap<String, String> product : availableProducts) {
            products.getItems().add(product.get("name") + " -> " + product.get("id"));
        }
    }

    public void addToProducts() {
        String id = products.getValue();
        if (!productsList.getItems().contains(id)) {
            productsList.getItems().add(id);
        }
    }

    public void sendRequest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date start = null;
        try {
            start = simpleDateFormat.parse(startDate.getText());
        } catch (ParseException parseException) {
            error.setText("invalid start date format");
            return;
        }
        Date end = null;
        try {
            end = simpleDateFormat.parse(endDate.getText());
        } catch (ParseException parseException) {
            error.setText("invalid end date format");
            return;
        }
        if (!discount.validate()) {
            error.setText("invalid discount amount format");
            return;
        }
        ArrayList<String> products = new ArrayList<>();
        for (String item : productsList.getItems()) {
            products.add(item.split(" -> ")[1]);
        }
        HashMap<String, String> off = new HashMap<>();
        off.put("start", startDate.getText());
        off.put("end", endDate.getText());
        off.put("discount", discount.getText());
        SellerController.getInstance().createAddOffRequest(products, off);
        error.setText("");
    }

}
