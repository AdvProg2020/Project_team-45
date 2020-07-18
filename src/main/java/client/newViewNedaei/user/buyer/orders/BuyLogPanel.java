package client.newViewNedaei.user.buyer.orders;

import client.newViewNedaei.Panel;
import client.controller.userControllers.BuyerController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

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
        HashMap<String, String> buyLog = BuyerController.getInstance().getCurrentBuyLog();
        id.setText(buyLog.get("id"));
        date.setText(new SimpleDateFormat("yyyy/MM/dd").format(buyLog.get("date")));
        buyer.setText(buyLog.get("buyerUsername"));
        discount.setText(buyLog.get("discountPercentage") + "%");
        finalPrice.setText("" + buyLog.get("finalPrice"));
        address.setText(buyLog.get("address"));
        phone.setText(buyLog.get("phoneNumber"));
        ArrayList<HashMap<String, String>> sellInfos =
                BuyerController.getInstance().getBuyLogSellInfosById(Integer.parseInt(buyLog.get("id")));
        for (HashMap<String, String> sellInfo : sellInfos) {
            products.getItems().add(sellInfo.get("productName") + " -> " + sellInfo.get("finalPrice"));
            ids.getItems().add(sellInfo.get("id"));
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
