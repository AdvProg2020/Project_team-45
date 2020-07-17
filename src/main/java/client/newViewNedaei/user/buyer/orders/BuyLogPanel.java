package client.newViewNedaei.user.buyer.orders;

import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import server.controller.userControllers.BuyerController;
import server.model.log.BuyLog;
import server.model.product.ProductSellInfo;

import java.text.SimpleDateFormat;

public class BuyLogPanel extends Panel {
    public Label date;
    public Label id;
    public Label buyer;
    public Label discount;
    public Label finalPrice;
    public Label address;
    public Label phone;
    public ListView<String> products;
    public ChoiceBox<String> ids;
    public ChoiceBox<String> scores;
    public Label error;

    public static String getFxmlFilePath() {
        return "/BuyLogPanel.fxml";
    }

    @FXML
    public void initialize() {
        BuyLog buyLog = BuyerController.getInstance().getCurrentBuyLog();
        id.setText(buyLog.getMainLog().getId());
        date.setText(new SimpleDateFormat("yyyy/MM/dd").format(buyLog.getMainLog().getDate()));
        buyer.setText(buyLog.getMainLog().getBuyerUsername());
        discount.setText(buyLog.getMainLog().getAppliedDiscountPercentage() + "%");
        finalPrice.setText("" + buyLog.getMainLog().getFinalPrice());
        address.setText(buyLog.getMainLog().getAddress());
        phone.setText(buyLog.getMainLog().getPhoneNumber());
        for (ProductSellInfo sellingProduct : buyLog.getMainLog().getSellingProducts()) {
            products.getItems().add(sellingProduct.getProduct() + " -> " + sellingProduct.getFinalPrice());
            ids.getItems().add(sellingProduct.getProduct().getId());
        }
        for (int i = 0; i < 6; i++) {
            scores.getItems().add("" + i);
        }
    }

    public void rate() {
        if (ids.getValue().equals("id to rate")) {
            error.setText("choose an id");
            return;
        } if (scores.getValue().equals("score")) {
            error.setText("choose a score");
            return;
        }
        BuyerController.getInstance().rateProductById(ids.getValue(), Integer.parseInt(scores.getValue()));
        error.setText("");
    }
}
