package client.newViewBagheri;

import client.controller.AuctionController;
import client.controller.ProductController;
import client.newViewNedaei.MenuController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class AuctionsMenu {
    private final AuctionController auctionController = AuctionController.getInstance();
    private final ProductController productController = ProductController.getInstance();
    private final MenuController menuController = MenuController.getInstance();
    public GridPane productsListPain;

    public static String getFxmlFilePath() {
        return "/AuctionsMenu.fxml";
    }

    @FXML
    public void initialize() {
        showProducts();
    }

    private void showProducts() {
        ArrayList<HashMap<String, String>> productInfosList = auctionController.getAuctionInfosList();
        int i = 0;
        for (HashMap<String, String> productInfo : productInfosList) {
            productsListPain.add(createProductInfoVBox(productInfo), i % 5, i / 5);
            i++;
        }
    }

    private VBox createProductInfoVBox(HashMap<String, String> AuctionInfo) {
        VBox productInfoVBox = new VBox();
        productInfoVBox.setBorder(new Border(new BorderStroke(Color.BROWN,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        int sizePrefWidth = 195;
        productInfoVBox.setPrefWidth(sizePrefWidth);
        productInfoVBox.setPrefHeight(350.0);
        ImageView productImageView = new ImageView(new Image(AuctionInfo.get("imageAddress")));
        productImageView.setOnMouseClicked(e -> goToProduct(AuctionInfo.get("id")));
        productImageView.setPreserveRatio(true);
        productImageView.setFitWidth(190.0);
        productImageView.setFitHeight(250.0);
        BorderPane imagePane = new BorderPane(productImageView);
        BorderPane.setAlignment(productImageView, Pos.CENTER);
        imagePane.setPrefWidth(sizePrefWidth);
        imagePane.setPrefHeight(250.0);
        int labelSize = 30;
        Label productName = new Label(AuctionInfo.get("name"));
        productName.setOnMouseClicked(e -> goToProduct(AuctionInfo.get("id")));
        setLabelStyle(productName, sizePrefWidth, labelSize);
        Label productScore = new Label("score: " + AuctionInfo.get("averageScore") + " out 0f 5");
        setLabelStyle(productScore, sizePrefWidth, labelSize);
        Label productPrice = new Label("base price: " + AuctionInfo.get("basePrice"));
        setLabelStyle(productPrice, sizePrefWidth, labelSize);
        Button participate = new Button("Participate in the auction");
        participate.setOnAction(e -> goToAuction(AuctionInfo.get("id")));
        productInfoVBox.getChildren().addAll(imagePane, productName, productScore, productPrice, participate);
        return productInfoVBox;
    }

    private void setLabelStyle(Label label, int prefWidth, int prefHeight) {
        label.setPrefWidth(prefWidth);
        label.setPrefHeight(prefHeight);
        label.setAlignment(Pos.CENTER);
    }

    private void goToProduct(String productId) {
        productController.setActiveProductBYProductIdForCategory(productId);
        menuController.goToMenu(ProductMenu.getFxmlFilePath());
    }

    private void goToAuction(String auctionId) {
        auctionController.setActiveAuctionById(auctionId);
        if (auctionController.hasActiveUserParticipatedInActiveAuction()) {
            menuController.goToPanel(ParticipateAuctionPanel.getFxmlFilePath());
        } else {
            menuController.goToMenu(AuctionMenu.getFxmlFilePath());
        }
    }
}