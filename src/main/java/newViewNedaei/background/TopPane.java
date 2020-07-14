package newViewNedaei.background;

import controller.CartController;
import controller.CategoryController;
import controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import newViewBagheri.OffMenu;
import newViewBagheri.ProductsMenu;
import newViewHatami.AdminMenu;
import newViewHatami.LoginRegisterMenu;
import newViewNedaei.MainMenu;
import newViewNedaei.MenuController;
import newViewNedaei.SoundPlayer;
import newViewNedaei.user.buyer.BuyerMenu;
import newViewNedaei.user.buyer.CartManagingMenu;
import newViewNedaei.user.seller.SellerMenu;

public class TopPane {
    public Button loginRegister;
    public Button mainMenu;
    public Button userPage;
    public Button viewCart;
    public Button logout;

    private final CategoryController categoryController = CategoryController.getInstance();
    private final MenuController menuController = MenuController.getInstance();
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
            String role = UserController.getActiveUser().getRole();
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
        for (String mainCategory : categoryController.getMainCategories()) {
            MenuItem menuItem = new MenuItem(mainCategory);
            menuItem.setOnAction(e -> goToCategory(mainCategory));
            categoryMenu.getItems().add(menuItem);
        }
    }

    public void addOffMenuItems() {
        for (String mainCategory : categoryController.getDiscountedMainCategories()) {
            MenuItem menuItem = new MenuItem(mainCategory);
            menuItem.setOnAction(e -> goToDiscountedCategory(mainCategory));
            offMenu.getItems().add(menuItem);
        }
    }

    private void goToCategory(String mainCategory) {
        categoryController.setActiveCategoryByName(mainCategory);
        categoryController.changeIsOffMenuToFalse();
        MenuController.getInstance().goToMenu(ProductsMenu.getFxmlFilePath());
    }

    private void goToDiscountedCategory(String mainCategory) {
        categoryController.setActiveCategoryByName(mainCategory);
        categoryController.changeIsOffMenuToTrue();
        MenuController.getInstance().goToMenu(OffMenu.getFxmlFilePath());
    }

    public Button getLoginRegister() {
        return loginRegister;
    }
}
