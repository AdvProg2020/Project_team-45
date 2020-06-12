package newViewHatami;

import graphicview.nedaei.MenuController;

public class AdminMenu extends AppMenu {

    public static String getFxmlFilePath() {
        return "/AdminMenu.fxml";
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

