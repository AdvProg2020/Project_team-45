package client.newViewNedaei.user.buyer;

import client.newViewHatami.Validator;
import client.newViewHatami.ValidatorField;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.user.buyer.discounts.DiscountCodesPanel;
import client.newViewNedaei.user.buyer.orders.OrdersManagingMenu;
import client.newViewNedaei.user.buyer.purchase.ReceiveInfoPanel;
import client.controller.userControllers.BuyerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class BuyerMenu {
    public ValidatorField balance;
    public Label error;
    public AnchorPane mainPane;

    public static String getFxmlFilePath() {
        return "/BuyerMenu.fxml";
    }

    @FXML
    public void initialize() {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/PersonalInfoPane.fxml"));
            pane.setTranslateX(0);
            pane.setTranslateY(0);
            mainPane.getChildren().add(pane);
            balance.setPromptText("" + BuyerController.getInstance().getBuyerBalance());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewOrders() {
        MenuController.getInstance().goToMenu(OrdersManagingMenu.getFxmlFilePath());
    }

    public void purchase() {
        MenuController.getInstance().goToPanel(ReceiveInfoPanel.getFxmlFilePath());
    }

    public void viewDiscounts() {
        MenuController.getInstance().goToPanel(DiscountCodesPanel.getFxmlFilePath());
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    public void editBalance() {
        if (!balance.validate()) {
            error.setText("invalid balance format");
            return;
        }
        BuyerController.getInstance().setBuyerBalance(Integer.parseInt(balance.getText()));
        balance.setPromptText("" + BuyerController.getInstance().getBuyerBalance());
        error.setText("");
    }
}
