package newViewHatami;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import newViewNedaei.MenuController;

import java.io.IOException;

public class AdminMenu {

    public Pane mainPane;

    public static String getFxmlFilePath() {
        return "/AdminMenu.fxml";
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

    public void manageUsersAction(){
        MenuController.getInstance().goToMenu(UsersManagingMenu.getFxmlFilePath());
    }

    public void manageDiscountCodesAction() {
        MenuController.getInstance().goToMenu(DiscountCodesManagingMenu.getFxmlFilePath());
    }

    public void manageCategoriesAction() {
        MenuController.getInstance().goToMenu(CategoriesManagingMenu.getFxmlFilePath());
    }

    public void manageProductsAction() {
        MenuController.getInstance().goToMenu(ProductsManagingMenuForAdmin.getFxmlFilePath());
    }

    public void manageRequestsAction() {
        MenuController.getInstance().goToMenu(RequestsManagingMenu.getFxmlFilePath());
    }
}

