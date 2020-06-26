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

    public static void deInitialize() {
        Platform.runLater(() ->
                MenuController.getInstance().getBackgroundPane().setStyle("-fx-background-color: rosybrown"));
    }

    public void goToUserAccount() {
        if (!UserController.isLoggedIn()) {
            MenuController.getInstance().goToMenu(LoginRegisterMenu.getFxmlFilePath());
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
    }

    public void goToProductsMenu() {
        MenuController.getInstance().goToMenu(ProductsMenu.getFxmlFilePath());
    }
}
