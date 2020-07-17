package client.newViewNedaei.user.buyer.purchase;

import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import server.controller.userControllers.BuyerController;

public class DiscountCodePanel extends Panel {
    public Label error;
    public TextField code;

    public static String getFxmlFilePath() {
        return "/DiscountCodePanel.fxml";
    }

    public void goNext() {
        if (!code.getText().equals("") && !BuyerController.getInstance().isDiscountCodeValid(code.getText())) {
            error.setText("invalid discount code");
            return;
        } if (!code.getText().equals("")) {
            BuyerController.getInstance().applyDiscountCode(code.getText());
        }
        MenuController.getInstance().removeCurrentPanel();
        MenuController.getInstance().goToPanel(PaymentPanel.getFxmlFilePath());
    }
}
