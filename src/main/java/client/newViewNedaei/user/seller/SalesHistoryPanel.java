package client.newViewNedaei.user.seller;

import client.controller.userControllers.SellerController;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

// nedaei: turned to new format successfully!
public class SalesHistoryPanel extends Panel {
    public Pane mainPane;
    public GridPane list;

    public static String getFxmlFilePath() {
        return "/SalesHistoryPanel.fxml";
    }

    @FXML
    public void initialize() {
        ArrayList<HashMap<String, String>> listOfSellLogs = SellerController.getInstance().getSellerSellLogs();
        int row = 1;
        for (HashMap<String, String> sellLog : listOfSellLogs) {
            list.add(createLabel(sellLog.get("date")), 0, row);
            list.add(createHyperLink(sellLog.get("id")), 1, row);
            list.add(createLabel(sellLog.get("finalPrice")), 2, row);
            list.add(createLabel(sellLog.get("buyerUsername")), 3, row);
            row++;
        }
    }

    private Hyperlink createHyperLink(String text) {
        Hyperlink hyperlink = new Hyperlink(text);
        hyperlink.setAlignment(Pos.CENTER);
        hyperlink.setPrefWidth(100);
        hyperlink.setPrefHeight(50);
        hyperlink.setOnAction(event -> {
            SellerController.getInstance().setCurrentSellLog(hyperlink.getText());
            MenuController.getInstance().goToPanel(SellLogPanel.getFxmlFilePath());
        });
        return hyperlink;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(100);
        label.setPrefHeight(50);
        return label;
    }
}
