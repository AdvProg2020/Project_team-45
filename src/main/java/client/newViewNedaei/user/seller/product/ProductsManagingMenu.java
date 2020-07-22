package client.newViewNedaei.user.seller.product;

import client.controller.ProductController;
import client.controller.userControllers.SellerController;
import client.newViewNedaei.MenuController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

// nedaei: turned to new format successfully!
public class ProductsManagingMenu {
    public GridPane mainPane;

    public static String getFxmlFilePath() {
        return "/ProductsManagingMenu.fxml";
    }

    @FXML
    public void initialize() {
        ArrayList<HashMap<String, String>> sellInfos = SellerController.getInstance().getSellInfos();
        int i = 0;
        for (HashMap<String, String> productSellInfo : sellInfos) {
            mainPane.add(createProductDisplay(productSellInfo), i%5, i/5);
            i++;
        }
    }

    private Pane createProductDisplay(HashMap<String, String> productSellInfo) {
//        Image image = null;
//        try {
//            image = new Image(productSellInfo.getProduct().getImageAddress());
//        } catch (Exception ignored) {
//
//        }
//        ImageView imageView = new ImageView(image);
//        imageView.setFitHeight(50);
//        imageView.setFitWidth(50);
//        imageView.setTranslateX(65);
//        imageView.setTranslateY(0);

        Label name = new Label();
        name.setPrefWidth(90);
        name.setPrefHeight(50);
        name.setAlignment(Pos.CENTER);
        name.setText(productSellInfo.get("name"));
        name.setTranslateX(0);
        name.setTranslateY(60);

        Label id = new Label();
        id.setPrefWidth(90);
        id.setPrefHeight(50);
        id.setAlignment(Pos.CENTER);
        id.setText("id: " + productSellInfo.get("id"));
        id.setTranslateX(90);
        id.setTranslateY(60);

        Button view = new Button("View");
        view.setPrefWidth(90);
        view.setPrefHeight(50);
        view.setTranslateX(0);
        view.setTranslateY(120);
        view.setOnMouseClicked(event -> {
            // todo: nedaei, view product
        });

        Button edit = new Button("Edit");
        edit.setPrefWidth(90);
        edit.setPrefHeight(50);
        edit.setTranslateX(90);
        edit.setTranslateY(120);
        edit.setOnMouseClicked(event -> {
            ProductController.getInstance().setActiveProductSellInfo(productSellInfo.get("id"));
            MenuController.getInstance().goToPanel(EditProductPanel.getFxmlFilePath());
        });

        Pane pane = new Pane();
//        pane.getChildren().add(imageView);
        pane.getChildren().add(name);
        pane.getChildren().add(id);
        pane.getChildren().add(view);
        pane.getChildren().add(edit);

        return pane;
    }
}
