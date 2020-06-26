package newViewNedaei;

import controller.userControllers.UserController;
import javafx.event.ActionEvent;
import newViewBagheri.ProductsMenu;
import newViewHatami.AdminMenu;
import newViewHatami.LoginRegisterMenu;
import newViewNedaei.user.buyer.BuyerMenu;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import newViewNedaei.user.seller.SellerMenu;

public class MainMenu{

    public static String getFxmlFilePath() {
        return "/MainMenu.fxml";
    }

    @FXML
    public void initialize() {
        Platform.runLater(() ->
                MenuController.getInstance().getBackgroundPane().setStyle("-fx-background-color: royalblue"));
    }

    public static void deInitialize() {
        Platform.runLater(() ->
                MenuController.getInstance().getBackgroundPane().setStyle("-fx-background-color: rosybrown"));
    }

}
