package client.newViewNedaei.user.buyer;

import client.newViewBagheri.ProductMenu;
import client.newViewHatami.LoginRegisterMenu;
import client.newViewHatami.Validator;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.user.buyer.purchase.ReceiveInfoPanel;
import client.controller.ProductController;
import client.controller.userControllers.BuyerController;
import client.controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

public class CartManagingMenu {
    public Label price;
    public GridPane grid;
    private final ArrayList<HashMap<String, String>> cart;

    public CartManagingMenu() {
        cart = BuyerController.getInstance().getCart();
    }

    public static String getFxmlFilePath() {
        return "/CartManagingMenu.fxml";
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    @FXML
    public void initialize() {
        int i = 0;
        for (HashMap<String, String> product : cart) {
            Pane pane = createProductDisplay(product);
            grid.add(pane, i%5, i/5);
            i++;
        }
        price.setText("" + BuyerController.getInstance().getCartTotalPrice());
    }

    private Pane createProductDisplay(HashMap<String, String> product) {
        Pane pane = new Pane();

        Label nameAndAmount = new Label();
        nameAndAmount.setPrefWidth(90);
        nameAndAmount.setPrefHeight(50);
        nameAndAmount.setAlignment(Pos.CENTER);
        nameAndAmount.setText(product.get("name") + " (" + product.get("amount") + ")");
        nameAndAmount.setTranslateX(0);
        nameAndAmount.setTranslateY(0);

        Label id = new Label();
        id.setPrefWidth(90);
        id.setPrefHeight(50);
        id.setAlignment(Pos.CENTER);
        id.setText("id: " + product.get("id"));
        id.setTranslateX(90);
        id.setTranslateY(0);

        Label singlePrice = new Label();
        singlePrice.setPrefWidth(90);
        singlePrice.setPrefHeight(50);
        singlePrice.setAlignment(Pos.CENTER);
        singlePrice.setText("fee: " + product.get("finalPrice"));
        singlePrice.setTranslateX(0);
        singlePrice.setTranslateY(60);

        Label totalPrice = new Label();
        totalPrice.setPrefWidth(90);
        totalPrice.setPrefHeight(50);
        totalPrice.setAlignment(Pos.CENTER);
        totalPrice.setText("total: " +
                Integer.parseInt(product.get("amount"))*Integer.parseInt(product.get("finalPrice")));
        totalPrice.setTranslateX(90);
        totalPrice.setTranslateY(60);

        Button increase = new Button("+");
        increase.setPrefWidth(60);
        increase.setPrefHeight(50);
        increase.setTranslateX(0);
        increase.setTranslateY(120);
        increase.setOnMouseClicked(event -> {

        });

        Button decrease = new Button("-");
        decrease.setPrefWidth(60);
        decrease.setPrefHeight(50);
        decrease.setTranslateX(60);
        decrease.setTranslateY(120);
        decrease.setOnMouseClicked(event -> {

        });

        Button view = new Button("View");
        view.setPrefWidth(60);
        view.setPrefHeight(50);
        view.setTranslateX(120);
        view.setTranslateY(120);
        ProductController.getInstance().setActiveProductById(Integer.parseInt(product.get("id")));
        view.setOnMouseClicked(event -> MenuController.getInstance().goToMenu(ProductMenu.getFxmlFilePath()));

        pane.getChildren().add(nameAndAmount);
        pane.getChildren().add(id);
        pane.getChildren().add(singlePrice);
        pane.getChildren().add(totalPrice);
        pane.getChildren().add(increase);
        pane.getChildren().add(decrease);
        pane.getChildren().add(view);

        return pane;
    }

    public void purchase() {
        if (!UserController.isLoggedIn()) {
            MenuController.getInstance().goToMenu(LoginRegisterMenu.getFxmlFilePath());
        } else {
            MenuController.getInstance().goToPanel(ReceiveInfoPanel.getFxmlFilePath());
        }
    }
}
