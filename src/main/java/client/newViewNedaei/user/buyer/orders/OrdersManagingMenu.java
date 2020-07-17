package client.newViewNedaei.user.buyer.orders;

import client.newViewNedaei.MenuController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import server.controller.userControllers.BuyerController;
import server.controller.userControllers.UserController;
import server.model.log.BuyLog;
import server.model.user.Buyer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrdersManagingMenu {
    @FXML
    private GridPane mainPane;

    public static String getFxmlFilePath() {
        return "/OrdersManagingMenu.fxml";
    }

    @FXML
    public void initialize() {
        ArrayList<BuyLog> buyLogs = ((Buyer) UserController.getActiveUser()).getListOfBuyLogs();
        int i = 0;
        for (BuyLog buyLog : buyLogs) {
            mainPane.add(createOrderDisplay(buyLog), i%5, i/5);
            i++;
        }
    }

    private Pane createOrderDisplay(BuyLog buyLog) {
        Label dateAndId = new Label();
        dateAndId.setPrefWidth(180);
        dateAndId.setPrefHeight(50);
        dateAndId.setAlignment(Pos.CENTER);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(buyLog.getMainLog());
        dateAndId.setText(simpleDateFormat.format(buyLog.getMainLog().getDate()) + " - id: " + buyLog.getMainLog().getId());
        dateAndId.setTranslateX(0);
        dateAndId.setTranslateY(0);

        Label finalPrice = new Label();
        finalPrice.setPrefWidth(180);
        finalPrice.setPrefHeight(50);
        finalPrice.setAlignment(Pos.CENTER);
        finalPrice.setText("" + buyLog.getMainLog().getFinalPrice());
        finalPrice.setTranslateX(0);
        finalPrice.setTranslateY(60);

        Button view = new Button("Show");
        view.setPrefWidth(90);
        view.setPrefHeight(50);
        view.setTranslateX(0);
        view.setTranslateY(120);
        view.setOnMouseClicked(event -> {
            BuyerController.getInstance().setCurrentBuyLog(buyLog);
            MenuController.getInstance().goToPanel(BuyLogPanel.getFxmlFilePath());
        });

//        Button rate = new Button("Rate");
//        rate.setPrefWidth(90);
//        rate.setPrefHeight(50);
//        rate.setTranslateX(90);
//        rate.setTranslateY(120);
//        rate.setOnMouseClicked(event -> {
//            BuyerController.getInstance().setCurrentBuyLog(buyLog);
//            MenuController.getInstance().goToPanel();
//        });

        Pane pane = new Pane();
        pane.getChildren().add(dateAndId);
        pane.getChildren().add(finalPrice);
        pane.getChildren().add(view);
//        pane.getChildren().add(rate);

        return pane;
    }
}
