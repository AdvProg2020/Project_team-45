package client.newViewNedaei.user.seller.off;

import client.controller.OffController;
import client.controller.userControllers.SellerController;
import client.newViewHatami.ViewOffForAdmin;
import client.newViewNedaei.MenuController;
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
public class OffsManagingMenu {
    public GridPane mainPane;

    public static String getFxmlFilePath() {
        return "/OffsManagingMenu.fxml";
    }

    @FXML
    public void initialize() {
        ArrayList<HashMap<String, String>> listOfOffs = SellerController.getInstance().getSellerOffs();
        int i = 0;
        for (HashMap<String, String> off : listOfOffs) {
            mainPane.add(createOffDisplay(off), i%5, i/5);
        }
    }

    private Pane createOffDisplay(HashMap<String, String> off) {
        Label idAndDiscount = new Label();
        idAndDiscount.setPrefWidth(180);
        idAndDiscount.setPrefHeight(50);
        idAndDiscount.setAlignment(Pos.CENTER);
        idAndDiscount.setText(off.get("id") + " - " + off.get("discountAmount") + "%");
        idAndDiscount.setTranslateX(0);
        idAndDiscount.setTranslateY(0);

        Label startAndEnd = new Label();
        startAndEnd.setPrefWidth(180);
        startAndEnd.setPrefHeight(50);
        startAndEnd.setAlignment(Pos.CENTER);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        startAndEnd.setText(simpleDateFormat.format(off.get("startTime")) + " - " + simpleDateFormat.format(off.get("endTime")));
        startAndEnd.setTranslateX(0);
        startAndEnd.setTranslateY(60);

        Button view = new Button("View");
        view.setPrefWidth(90);
        view.setPrefHeight(50);
        view.setTranslateY(120);
        view.setTranslateX(0);
        view.setOnMouseClicked(event -> {
            ViewOffForAdmin.setViewingOff(off.get("id"));
            MenuController.getInstance().goToPanel(ViewOffForAdmin.getFxmlFilePath());
        });

        Button edit = new Button("Edit");
        edit.setPrefWidth(90);
        edit.setPrefHeight(50);
        edit.setTranslateY(120);
        edit.setTranslateX(90);
        edit.setOnMouseClicked(event -> {
            OffController.getInstance().setCurrentOff(off.get("id"));
            MenuController.getInstance().goToPanel(EditOffPanel.getFxmlFilePath());
        });

        Pane pane = new Pane();
        pane.getChildren().add(idAndDiscount);
        pane.getChildren().add(startAndEnd);
        pane.getChildren().add(view);
        pane.getChildren().add(edit);

        return pane;
    }
}
