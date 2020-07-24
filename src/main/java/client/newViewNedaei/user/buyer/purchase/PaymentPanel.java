package client.newViewNedaei.user.buyer.purchase;

import client.controller.userControllers.BuyerController;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import client.newViewNedaei.user.buyer.orders.BuyLogPanel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

// nedaei: turned to new format successfully!
public class PaymentPanel extends Panel {
    public Label message;

    public PaymentPanel() {
    }

    public static String getFxmlFilePath() {
        return "/PaymentPanel.fxml";
    }

    @FXML
    public void initialize() {
        if (!BuyerController.getInstance().canPurchase()) {
            message.setTextFill(Color.RED);
            message.setText("do not have enough wallet balance!");
            return;
        }
        message.setStyle("-fx-background-color: white");
        message.setTextFill(Color.GREEN);
        message.setText("purchased successfully!");
        BuyerController.getInstance().purchase();
    }

    public void showDetails() {
        BuyerController.getInstance().setCurrentBuyLog();
        MenuController.getInstance().goToPanel(BuyLogPanel.getFxmlFilePath());
    }
}
