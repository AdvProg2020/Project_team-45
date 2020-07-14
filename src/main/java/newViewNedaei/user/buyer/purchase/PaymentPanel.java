package newViewNedaei.user.buyer.purchase;

import controller.userControllers.BuyerController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.log.BuyLog;
import model.log.Log;
import newViewNedaei.MenuController;
import newViewNedaei.Panel;
import newViewNedaei.user.buyer.orders.BuyLogPanel;

public class PaymentPanel extends Panel {
    public Label message;
    private final Log log;

    public PaymentPanel() {
        log = BuyerController.getInstance().getLog();
    }

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
        message.setStyle("-fx-background-color: white");
        message.setTextFill(Color.GREEN);
        message.setText("purchased successfully!");
        BuyerController.getInstance().purchase();
    }

    public void showDetails() {
        BuyerController.getInstance().setCurrentBuyLog(new BuyLog(log));
        MenuController.getInstance().goToPanel(BuyLogPanel.getFxmlFilePath());
    }
}
