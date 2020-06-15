package newViewNedaei.user.buyer;

import controller.userControllers.BuyerController;
import controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.user.Buyer;
import newViewHatami.Validator;
import newViewHatami.ValidatorField;
import newViewNedaei.MenuController;
import newViewNedaei.user.buyer.purchase.ReceiveInfoPanel;

import java.io.IOException;

public class BuyerMenu {
    public ValidatorField balance;
    public Label error;
    @FXML
    private AnchorPane mainPane;

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
        } catch (IOException ignored) {

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
        ((Buyer) UserController.getActiveUser()).setBalance(Integer.parseInt(balance.getText()));
        balance.setPromptText("" + BuyerController.getInstance().getBuyerBalance());
        error.setText("");
    }
}
