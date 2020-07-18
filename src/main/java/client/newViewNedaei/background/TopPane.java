package client.newViewNedaei.background;

import client.newViewBagheri.OffMenu;
import client.newViewBagheri.ProductsMenu;
import client.newViewHatami.AdminMenu;
import client.newViewHatami.LoginRegisterMenu;
import client.newViewNedaei.MainMenu;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.SoundPlayer;
import client.newViewNedaei.user.buyer.BuyerMenu;
import client.newViewNedaei.user.buyer.CartManagingMenu;
import client.newViewNedaei.user.seller.SellerMenu;
import client.controller.CartController;
import client.controller.CategoryController;
import client.controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class TopPane {
    public Button loginRegister;
    public Button mainMenu;
    public Button userPage;
    public Button viewCart;
    public Button logout;

    public Menu categoryMenu;
    public Menu offMenu;

    public static String getFxmlFilePath() {
        return "/TopPane.fxml";
    }

    @FXML
    public void initialize() {
        loginRegister.setVisible(true);
        logout.setVisible(true);
        if (UserController.isLoggedIn()) {
            loginRegister.setVisible(false);
        } else {
            logout.setVisible(false);
        }
        addMenuItems();
    }

    public void goBack() {
        MenuController.getInstance().goBack();
    }

    public void click() {
        SoundPlayer.playClickEffect();
    }

    public void goToLoginRegister() {
        MenuController.getInstance().goToMenu(LoginRegisterMenu.getFxmlFilePath());
    }

    public void goToMainMenu() {
        MenuController.getInstance().goToMenu(MainMenu.getFxmlFilePath());
    }

    public void goToUserPage() {
        if (!UserController.isLoggedIn()) {
            MenuController.getInstance().goToMenu(LoginRegisterMenu.getFxmlFilePath());
        } else {
            String role = UserController.getInstance().getRole();
            switch (role) {
                case "admin":
                    MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
                    break;
                case "seller":
                    MenuController.getInstance().goToMenu(SellerMenu.getFxmlFilePath());
                    break;
                case "buyer":
                    MenuController.getInstance().goToMenu(BuyerMenu.getFxmlFilePath());
                    break;
            }
        }
    }

    public void gotoCart() {
        MenuController.getInstance().goToMenu(CartManagingMenu.getFxmlFilePath());
    }

    public void logItOut() {
        UserController.getInstance().logout();
        loginRegister.setDisable(false);
        logout.setDisable(true);
        CartController.getInstance().resetCart();
        MenuController.getInstance().goToMenu(MainMenu.getFxmlFilePath());
    }

    public void addMenuItems() {
        addCategoryMenuItems();
        addOffMenuItems();
    }

    private void addCategoryMenuItems() {
        for (String mainCategory : CategoryController.getInstance().getMainCategories()) {
            MenuItem menuItem = new MenuItem(mainCategory);
            menuItem.setOnAction(e -> goToCategory(mainCategory));
            categoryMenu.getItems().add(menuItem);
        }
    }

    public void addOffMenuItems() {
        for (String mainCategory : CategoryController.getInstance().getDiscountedMainCategories()) {
            MenuItem menuItem = new MenuItem(mainCategory);
            menuItem.setOnAction(e -> goToDiscountedCategory(mainCategory));
            offMenu.getItems().add(menuItem);
        }
    }

    private void goToCategory(String mainCategory) {
        CategoryController.getInstance().setActiveCategoryByName(mainCategory);
        CategoryController.getInstance().changeIsOffMenuToFalse();
        MenuController.getInstance().goToMenu(ProductsMenu.getFxmlFilePath());
    }

    private void goToDiscountedCategory(String mainCategory) {
        CategoryController.getInstance().setActiveCategoryByName(mainCategory);
        CategoryController.getInstance().changeIsOffMenuToTrue();
        MenuController.getInstance().goToMenu(OffMenu.getFxmlFilePath());
    }

}
