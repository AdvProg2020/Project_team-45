package newViewNedaei.user.buyer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import newViewNedaei.MenuController;

import java.io.IOException;

public class BuyerMenu {
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
        } catch (IOException ignored) {

        }
    }

    public void viewOrders() {
        MenuController.getInstance().goToMenu(OrdersManagingMenu.getFxmlFilePath());
    }
}
