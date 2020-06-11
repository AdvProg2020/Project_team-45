package graphicview.nedaei;

import graphicview.nedaei.user.BuyerMenu;
import graphicview.nedaei.user.PersonalInfoPane;
import graphicview.nedaei.user.SellerMenu;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
//        if (!UserController.isLoggedIn()) {
//            return;
//        }
//        String role = UserController.getActiveUser().getRole();
        String role = "seller";
        if (role.equals("admin")) {

        } else if (role.equals("seller")) {
            MenuController.getInstance().goToMenu(PersonalInfoPane.getFxmlFilePath());
        } else if (role.equals("buyer")) {
            MenuController.getInstance().goToMenu(PersonalInfoPane.getFxmlFilePath());
        }
        deInitialize();
    }
}
