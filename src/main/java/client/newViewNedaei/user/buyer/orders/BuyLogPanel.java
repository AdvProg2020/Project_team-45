package client.newViewNedaei.user.buyer.orders;

import client.controller.P2PController;
import client.controller.userControllers.BuyerController;
import client.newViewNedaei.Panel;
import com.google.gson.internal.LinkedTreeMap;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;

// nedaei: turned to new format successfully!
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

    private ArrayList sellInfos;

    public static String getFxmlFilePath() {
        return "/BuyLogPanel.fxml";
    }

    @FXML
    public void initialize() {
        HashMap<String, String> buyLog = BuyerController.getInstance().getCurrentBuyLog();

        id.setText(buyLog.get("id"));
        date.setText(buyLog.get("date"));
        buyer.setText(buyLog.get("buyerUsername"));
        discount.setText(buyLog.get("discountPercentage") + "%");
        finalPrice.setText(buyLog.get("finalPrice"));
        address.setText(buyLog.get("address"));
        phone.setText(buyLog.get("phoneNumber"));

        sellInfos = BuyerController.getInstance().getBuyLogSellInfosById(buyLog.get("id"));
        for (Object sellInfo : sellInfos) {
            products.getItems().add(new HashMap<String, String>((LinkedTreeMap)sellInfo).get("productName") + " -> " + new HashMap<String, String>((LinkedTreeMap)sellInfo).get("finalPrice"));
            ids.getItems().add(new HashMap<String, String>((LinkedTreeMap)sellInfo).get("id"));
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

    public void downloadFiles(MouseEvent mouseEvent) {
        for (Object sellInfo : sellInfos) {
            if (new HashMap<String, String>((LinkedTreeMap)sellInfo).get("isFile").equals("true")) {
                P2PController.getInstance().receiveFile((new HashMap<String, String>((LinkedTreeMap)sellInfo)).get("id"));
            }
        }
    }
}
