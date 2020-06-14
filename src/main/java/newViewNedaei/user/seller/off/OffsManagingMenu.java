package newViewNedaei.user.seller.off;

import controller.OffController;
import controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Off;
import model.user.Seller;
import newViewNedaei.MenuController;
import newViewNedaei.user.seller.off.EditOffPanel;

import java.text.SimpleDateFormat;

public class OffsManagingMenu {
    @FXML
    private GridPane mainPane;

    public static String getFxmlFilePath() {
        return "/OffsManagingMenu.fxml";
    }

    @FXML
    public void initialize() {
        Seller seller = (Seller) UserController.getActiveUser();
        int i = 0;
        for (Off off : seller.getListOfOffs().values()) {
            mainPane.add(createOffDisplay(off), i%5, i/5);
        }
    }

    private Pane createOffDisplay(Off off) {
        Label idAndDiscount = new Label();
        idAndDiscount.setPrefWidth(180);
        idAndDiscount.setPrefHeight(50);
        idAndDiscount.setAlignment(Pos.CENTER);
        idAndDiscount.setText(off.getId() + " - " + off.getDiscountAmount() + "%");
        idAndDiscount.setTranslateX(0);
        idAndDiscount.setTranslateY(0);

        Label startAndEnd = new Label();
        startAndEnd.setPrefWidth(180);
        startAndEnd.setPrefHeight(50);
        startAndEnd.setAlignment(Pos.CENTER);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        startAndEnd.setText(simpleDateFormat.format(off.getStartTime()) + " - " + simpleDateFormat.format(off.getEndTime()));
        startAndEnd.setTranslateX(0);
        startAndEnd.setTranslateY(60);

        Button view = new Button("View");
        view.setPrefWidth(90);
        view.setPrefHeight(50);
        view.setTranslateY(120);
        view.setTranslateX(0);
//        view.setOnMouseClicked(event -> );

        Button edit = new Button("Edit");
        edit.setPrefWidth(90);
        edit.setPrefHeight(50);
        edit.setTranslateY(120);
        edit.setTranslateX(90);
        edit.setOnMouseClicked(event -> {
            OffController.getInstance().setCurrentOff(off);
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
