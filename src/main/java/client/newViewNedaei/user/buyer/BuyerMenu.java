package client.newViewNedaei.user.buyer;

import client.newViewHatami.Validator;
import client.newViewHatami.ValidatorField;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.user.buyer.discounts.DiscountCodesPanel;
import client.newViewNedaei.user.buyer.orders.OrdersManagingMenu;
import client.newViewNedaei.user.buyer.purchase.ReceiveInfoPanel;
import client.controller.userControllers.BuyerController;
import client.controller.BankController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

// nedaei: turned to new format successfully!
public class BuyerMenu {
    public Label balance;
    public Label error;
    public AnchorPane mainPane;
    public ValidatorField charge;
    public Label accountBalance;

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
            updateWalletAndAccount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateWalletAndAccount() {
        balance.setText("" + BuyerController.getInstance().getBuyerBalance());
        accountBalance.setText("" + BuyerController.getInstance().getBuyerAccountBalance());
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


    public void chargeWallet(MouseEvent mouseEvent) {
        if (!charge.validate()) {
            error.setText("invalid charge format");
            return;
        }
        try {
            BankController.getInstance().chargeWallet(Integer.parseInt(charge.getText()));
            updateWalletAndAccount();
            error.setText("");
        } catch (Throwable e) {
            error.setText(e.getMessage());
        }
    }
}
