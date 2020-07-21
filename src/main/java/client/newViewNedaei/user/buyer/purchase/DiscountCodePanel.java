package client.newViewNedaei.user.buyer.purchase;

import client.controller.userControllers.BuyerController;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

// nedaei: turned to new format successfully!
public class DiscountCodePanel extends Panel {
    public Label error;
    public TextField code;

    public static String getFxmlFilePath() {
        return "/DiscountCodePanel.fxml";
    }

    public void goNext() {
        if (!BuyerController.getInstance().isDiscountCodeValid(code.getText())) {
            error.setText("invalid discount code");
            return;
        }
        BuyerController.getInstance().applyDiscountCode(code.getText());
        MenuController.getInstance().removeCurrentPanel();
        MenuController.getInstance().goToPanel(PaymentPanel.getFxmlFilePath());
    }
}
