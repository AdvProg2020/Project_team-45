package client.newViewNedaei.user.buyer.orders;

import client.newViewNedaei.MenuController;
import client.controller.userControllers.BuyerController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

// nedaei: turned to new format successfully!
// todo: handle rating
public class OrdersManagingMenu {
    public GridPane mainPane;

    public static String getFxmlFilePath() {
        return "/OrdersManagingMenu.fxml";
    }

    @FXML
    public void initialize() {
        ArrayList<HashMap<String, String>> buyLogs = BuyerController.getInstance().getListOfBuyLogs();
        int i = 0;
        for (HashMap<String, String> buyLog : buyLogs) {
            mainPane.add(createOrderDisplay(buyLog), i%5, i/5);
            i++;
        }
    }

    private Pane createOrderDisplay(HashMap<String, String> buyLog) {
        Label dateAndId = new Label();
        dateAndId.setPrefWidth(180);
        dateAndId.setPrefHeight(50);
        dateAndId.setAlignment(Pos.CENTER);
        dateAndId.setText(buyLog.get("date") + " - id: " + buyLog.get("id"));
        dateAndId.setTranslateX(0);
        dateAndId.setTranslateY(0);

        Label finalPrice = new Label();
        finalPrice.setPrefWidth(180);
        finalPrice.setPrefHeight(50);
        finalPrice.setAlignment(Pos.CENTER);
        finalPrice.setText("" + buyLog.get("finalPrice"));
        finalPrice.setTranslateX(0);
        finalPrice.setTranslateY(60);

        Button view = new Button("Show");
        view.setPrefWidth(90);
        view.setPrefHeight(50);
        view.setTranslateX(0);
        view.setTranslateY(120);
        view.setOnMouseClicked(event -> {
            BuyerController.getInstance().setCurrentBuyLogById(buyLog.get("id"));
            MenuController.getInstance().goToPanel(BuyLogPanel.getFxmlFilePath());
        });

        Button rate = new Button("Rate");
        rate.setPrefWidth(90);
        rate.setPrefHeight(50);
        rate.setTranslateX(90);
        rate.setTranslateY(120);
        rate.setOnMouseClicked(event -> {
            // rate
        });

        Pane pane = new Pane();
        pane.getChildren().add(dateAndId);
        pane.getChildren().add(finalPrice);
        pane.getChildren().add(view);
        pane.getChildren().add(rate);

        return pane;
    }
}
