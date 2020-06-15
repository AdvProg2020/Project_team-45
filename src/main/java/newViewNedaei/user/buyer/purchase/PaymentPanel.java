package newViewNedaei.user.buyer.purchase;

import controller.userControllers.BuyerController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import newViewNedaei.Panel;

public class PaymentPanel extends Panel {
    public Label message;

    public static String getFxmlFilePath() {
        return "/PaymentPanel.fxml";
    }

    @FXML
    public void initialize() {
        if (!BuyerController.getInstance().canPurchase()) {
            message.setTextFill(Color.RED);
            message.setText("do not have enough balance!");
            return;
        }
        message.setTextFill(Color.GREEN);
        message.setText("purchased successfully!");
        BuyerController.getInstance().purchase();
    }
}
