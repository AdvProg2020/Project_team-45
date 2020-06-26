package newViewNedaei.user.buyer;

import controller.ProductController;
import controller.userControllers.BuyerController;
import controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.product.Product;
import model.product.ProductSellInfo;
import model.user.Cart;
import newViewBagheri.ProductMenu;
import newViewHatami.LoginRegisterMenu;
import newViewHatami.Validator;
import newViewNedaei.MenuController;
import newViewNedaei.user.buyer.purchase.ReceiveInfoPanel;

import java.util.HashMap;

public class CartManagingMenu {
    public Label price;
    public GridPane grid;
    private final Cart cart;
//    private HashMap<>

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
        for (ProductSellInfo productSellInfo : cart.getProductSellInfos()) {
            grid.add(createProductDisplay(productSellInfo), i%5, i/5);
            i++;
        }
        price.setText("" + cart.getTotalPrice());
    }

    private Pane createProductDisplay(ProductSellInfo productSellInfo) {
        Product product = productSellInfo.getProduct();
        Label nameAndAmount = new Label();
        nameAndAmount.setPrefWidth(180);
        nameAndAmount.setPrefHeight(50);
        nameAndAmount.setAlignment(Pos.CENTER);
        nameAndAmount.setText(product.getName() + "(" + cart.getProductAmountById(productSellInfo.getId()) + ")");
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
        ProductSellInfo productSellInfo1 = productSellInfo;
        increase.setOnMouseClicked(event -> {
            increaseProduct(product, nameAndAmount, productSellInfo1);
        });

        Button decrease = new Button("-");
        decrease.setPrefWidth(60);
        decrease.setPrefHeight(50);
        decrease.setTranslateX(60);
        decrease.setTranslateY(120);
        decrease.setOnMouseClicked(event -> {
            decreaseProduct(product, nameAndAmount, productSellInfo1);
        });

        Button view = new Button("View");
        view.setPrefWidth(60);
        view.setPrefHeight(50);
        view.setTranslateX(120);
        view.setTranslateY(120);
        ProductController.getInstance().setActiveProduct(product);
        view.setOnMouseClicked(event -> MenuController.getInstance().goToMenu(ProductMenu.getFxmlFilePath()));

        Pane pane = new Pane();
        pane.getChildren().add(nameAndAmount);
        pane.getChildren().add(id);
        pane.getChildren().add(increase);
        pane.getChildren().add(decrease);
        pane.getChildren().add(view);

        return pane;
    }

    private void decreaseProduct(Product product, Label nameAndAmount, ProductSellInfo productSellInfo1) {
        if (cart.getProductAmountById(productSellInfo1.getId()) > 1) {
            BuyerController.getInstance().decreaseCartProductById(productSellInfo1.getId());
            nameAndAmount.setText(product.getName() + "(" + cart.getProductAmountById(productSellInfo1.getId()) + ")");
            price.setText("" + cart.getTotalPrice());
        } else {

        }
    }

    private void increaseProduct(Product product, Label nameAndAmount, ProductSellInfo productSellInfo1) {
        if (cart.getProductAmountById(productSellInfo1.getId()) < productSellInfo1.getStock()) {
            BuyerController.getInstance().increaseCartProductById(productSellInfo1.getId());
            nameAndAmount.setText(product.getName() + "(" + cart.getProductAmountById(productSellInfo1.getId()) + ")");
            price.setText("" + cart.getTotalPrice());
        }
    }

    public void purchase() {
        if (!UserController.isLoggedIn()) {
            MenuController.getInstance().goToMenu(LoginRegisterMenu.getFxmlFilePath());
        } else {
            MenuController.getInstance().goToPanel(ReceiveInfoPanel.getFxmlFilePath());
        }
    }
}
