package newViewNedaei.user.seller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import newViewNedaei.MenuController;

import java.io.IOException;

public class SellerMenu {
    @FXML
    private Button offsViewing;
    @FXML
    private Button productsManaging;
    @FXML
    private AnchorPane mainPane;

    public static String getFxmlFilePath() {
        return "/SellerMenu.fxml";
    }

    @FXML
    public void initialize() {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/PersonalInfoPane.fxml"));
            pane.setTranslateX(0);
            pane.setTranslateY(0);
            mainPane.getChildren().add(pane);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }


    public void manageProducts() {
        MenuController.getInstance().goToMenu(ProductsManagingMenu.getFxmlFilePath());
    }

    public void viewOffs() {
        MenuController.getInstance().goToMenu(OffsManagingMenu.getFxmlFilePath());
    }
}
