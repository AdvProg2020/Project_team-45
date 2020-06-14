package newViewNedaei;

import controller.userControllers.UserController;
import newViewHatami.AdminMenu;
import newViewNedaei.user.buyer.BuyerMenu;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import newViewNedaei.user.seller.SellerMenu;

public class MainMenu{
    @FXML
    private Button userAccount;
    @FXML
    private Button products;
    @FXML
    private Button offs;

    public static String getFxmlFilePath() {
        return "/MainMenu.fxml";
    }

    @FXML
    public void initialize() {
        Platform.runLater(() ->
                MenuController.getInstance().getBackgroundPane().setStyle("-fx-background-color: royalblue"));
    }

    public void deInitialize() {
        Platform.runLater(() ->
                MenuController.getInstance().getBackgroundPane().setStyle("-fx-background-color: rosybrown"));
    }

    public void goToUserAccount() {
        if (!UserController.isLoggedIn()) {
            return;
        }
        String role = UserController.getActiveUser().getRole();
        if (role.equals("admin")) {
            MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
        } else if (role.equals("seller")) {
            MenuController.getInstance().goToMenu(SellerMenu.getFxmlFilePath());
        } else if (role.equals("buyer")) {
            MenuController.getInstance().goToMenu(BuyerMenu.getFxmlFilePath());
        }
        deInitialize();
    }
}
