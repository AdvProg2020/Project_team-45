package client.newViewNedaei.user.buyer.purchase;

import client.controller.userControllers.BuyerController;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

// nedaei: turned to new format successfully!
public class DiscountCodePanel extends Panel {
    public Label error;
    public TextField code;
    public ChoiceBox<String> walletOrAccount;

    public static String getFxmlFilePath() {
        return "/DiscountCodePanel.fxml";
    }

    @FXML
    public void initialize() {
        walletOrAccount.getItems().add("wallet");
        walletOrAccount.getItems().add("account");
    }

    public void goNext() {
        if (!BuyerController.getInstance().isDiscountCodeValid(code.getText())) {
            error.setText("invalid discount code");
            return;
        } if (walletOrAccount.getValue().equals("account") && !BuyerController.getInstance().canPurchaseByAccount()) {
            error.setText("not enough account balance");
            return;
        }
        BuyerController.getInstance().applyDiscountCode(code.getText(), walletOrAccount.getValue());
        MenuController.getInstance().removeCurrentPanel();
        MenuController.getInstance().goToPanel(PaymentPanel.getFxmlFilePath());
    }
}
