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
    private HashMap<Pane, ProductSellInfo> sellInfos;

    public CartManagingMenu() {
        cart = BuyerController.getInstance().getCart();
        sellInfos = new HashMap<>();
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
            Pane pane = createProductDisplay(productSellInfo);
            grid.add(pane, i%5, i/5);
            i++;
        }
        price.setText("" + cart.getTotalPrice());
    }

    private Pane createProductDisplay(ProductSellInfo productSellInfo) {
        Pane pane = new Pane();
        sellInfos.put(pane, productSellInfo);

        Label nameAndAmount = new Label();
        nameAndAmount.setPrefWidth(90);
        nameAndAmount.setPrefHeight(50);
        nameAndAmount.setAlignment(Pos.CENTER);
        nameAndAmount.setText(sellInfos.get(pane).getProduct().getName() + "(" + cart.getProductAmountById(productSellInfo.getId()) + ")");
        nameAndAmount.setTranslateX(0);
        nameAndAmount.setTranslateY(0);

        Label id = new Label();
        id.setPrefWidth(90);
        id.setPrefHeight(50);
        id.setAlignment(Pos.CENTER);
        id.setText("id: " + sellInfos.get(pane).getProduct().getId());
        id.setTranslateX(90);
        id.setTranslateY(0);

        Label singlePrice = new Label();
        singlePrice.setPrefWidth(90);
        singlePrice.setPrefHeight(50);
        singlePrice.setAlignment(Pos.CENTER);
        singlePrice.setText("fee: " + sellInfos.get(pane).getFinalPrice());
        singlePrice.setTranslateX(0);
        singlePrice.setTranslateY(60);

        Label totalPrice = new Label();
        totalPrice.setPrefWidth(90);
        totalPrice.setPrefHeight(50);
        totalPrice.setAlignment(Pos.CENTER);
        totalPrice.setText("total: " + cart.getTotalPrice());
        totalPrice.setTranslateX(90);
        totalPrice.setTranslateY(60);

        Button increase = new Button("+");
        increase.setPrefWidth(60);
        increase.setPrefHeight(50);
        increase.setTranslateX(0);
        increase.setTranslateY(120);
        increase.setOnMouseClicked(event -> {
            Product product = sellInfos.get(pane).getProduct();
            increaseProduct(product, nameAndAmount, sellInfos.get(pane));
            totalPrice.setText("total: " + cart.getTotalPrice());
        });

        Button decrease = new Button("-");
        decrease.setPrefWidth(60);
        decrease.setPrefHeight(50);
        decrease.setTranslateX(60);
        decrease.setTranslateY(120);
        decrease.setOnMouseClicked(event -> {
            Product product = sellInfos.get(pane).getProduct();
            decreaseProduct(pane, product, nameAndAmount, sellInfos.get(pane));
            totalPrice.setText("total: " + cart.getTotalPrice());
        });

        Button view = new Button("View");
        view.setPrefWidth(60);
        view.setPrefHeight(50);
        view.setTranslateX(120);
        view.setTranslateY(120);
        ProductController.getInstance().setActiveProduct(sellInfos.get(pane).getProduct());
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

    private void decreaseProduct(Pane pane, Product product, Label nameAndAmount, ProductSellInfo productSellInfo1) {
        BuyerController.getInstance().decreaseCartProductById(productSellInfo1.getId());
        nameAndAmount.setText(product.getName() + "(" + cart.getProductAmountById(productSellInfo1.getId()) + ")");
        price.setText("" + cart.getTotalPrice());
        if (cart.getProductAmountById(productSellInfo1.getId()) == 0) {
            grid.getChildren().remove(pane);
            cart.removeProduct(productSellInfo1);
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
