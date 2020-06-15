package newViewNedaei.user.buyer;

import controller.userControllers.BuyerController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.product.Product;
import model.user.Cart;
import newViewHatami.Validator;
import newViewNedaei.MenuController;
import newViewNedaei.user.buyer.purchase.ReceiveInfoPanel;

public class CartManagingMenu {
    public Label price;
    public GridPane grid;
    private final Cart cart;

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
        for (Product product : cart.getCartProducts()) {
            grid.add(createProductDisplay(product), i%5, i/5);
            i++;
        }
        price.setText("" + cart.getTotalPrice());
    }

    private Pane createProductDisplay(Product product) {
        Label nameAndAmount = new Label();
        nameAndAmount.setPrefWidth(180);
        nameAndAmount.setPrefHeight(50);
        nameAndAmount.setAlignment(Pos.CENTER);
        nameAndAmount.setText(product.getName() + "(" + cart.getProductAmountById(product.getId()) + ")");
        nameAndAmount.setTranslateX(0);
        nameAndAmount.setTranslateY(0);

        Label id = new Label();
        id.setPrefWidth(180);
        id.setPrefHeight(50);
        id.setAlignment(Pos.CENTER);
        id.setText("id: " + product.getId());
        id.setTranslateX(0);
        id.setTranslateY(60);

        Button increase = new Button("+");
        increase.setPrefWidth(60);
        increase.setPrefHeight(50);
        increase.setTranslateX(0);
        increase.setTranslateY(120);
        increase.setOnMouseClicked(event -> {
            BuyerController.getInstance().increaseCartProductById(product.getId());
            nameAndAmount.setText(product.getName() + "(" + cart.getProductAmountById(product.getId()) + ")");
        });

        Button decrease = new Button("-");
        decrease.setPrefWidth(60);
        decrease.setPrefHeight(50);
        decrease.setTranslateX(60);
        decrease.setTranslateY(120);
        decrease.setOnMouseClicked(event -> {
            BuyerController.getInstance().decreaseCartProductById(product.getId());
            nameAndAmount.setText(product.getName() + "(" + cart.getProductAmountById(product.getId()) + ")");
        });

        Button view = new Button("View");
        view.setPrefWidth(60);
        view.setPrefHeight(50);
        view.setTranslateX(120);
        view.setTranslateY(120);
//        view.setOnMouseClicked(event -> );

        Pane pane = new Pane();
        pane.getChildren().add(nameAndAmount);
        pane.getChildren().add(id);
        pane.getChildren().add(increase);
        pane.getChildren().add(decrease);
        pane.getChildren().add(view);

        return pane;
    }

    public void purchase() {
        MenuController.getInstance().goToPanel(ReceiveInfoPanel.getFxmlFilePath());
    }
}
