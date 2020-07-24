package client.newViewBagheri;

import client.controller.AuctionController;
import client.controller.userControllers.SellerController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;

public class AddAuctionPanel extends Panel {
    public ChoiceBox<String> products;
    public TextField endDate;
    public TextField basePrice;
    public Label error;

    public static String getFxmlFilePath() {
        return "/AddAuctionPanel.fxml";
    }

    @FXML
    public void initialize() {
        addProductsToChoiceBox();
    }

    private void addProductsToChoiceBox() {
        ArrayList<HashMap<String, String>> availableProducts = SellerController.getInstance().getAvailableProducts();
        for (HashMap<String, String> product : availableProducts) {
            products.getItems().add(product.get("name") + " -> " + product.get("id"));
        }
    }

    public void addAuction() {
        // TODO: add errors
        HashMap<String, String> auctionInfo = new HashMap<>();
        auctionInfo.put("productId", products.getValue().split(" -> ")[1]);
        auctionInfo.put("endDate", endDate.getText());
        auctionInfo.put("basePrice", basePrice.getText());
        AuctionController.getInstance().createAuction(auctionInfo);
        error.setText("");
    }
}
