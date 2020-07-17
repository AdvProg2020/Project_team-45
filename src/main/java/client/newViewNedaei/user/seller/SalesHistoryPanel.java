package client.newViewNedaei.user.seller;


import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import server.controller.userControllers.SellerController;
import server.controller.userControllers.UserController;
import server.model.log.SellLog;
import server.model.user.Seller;

import java.text.SimpleDateFormat;


public class SalesHistoryPanel extends Panel {
    @FXML
    private Pane mainPane;
    @FXML
    private GridPane list;

    public static String getFxmlFilePath() {
        return "/SalesHistoryPanel.fxml";
    }

    @FXML
    public void initialize() {
        Seller seller = (Seller) UserController.getActiveUser();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        int row = 1;
        for (SellLog sellLog : seller.getListOfSellLogs()) {
            list.add(createLabel(simpleDateFormat.format(sellLog.getMainLog().getDate())), 0, row);
            list.add(createHyperLink(sellLog.getMainLog().getId()), 1, row);
            list.add(createLabel("" + sellLog.getMainLog().getFinalPrice()), 2, row);
            list.add(createLabel(sellLog.getMainLog().getBuyerUsername()), 3, row);
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
