package newViewBagheri;

import controller.CategoryController;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import newViewNedaei.MainMenu;
import newViewNedaei.MenuController;

import java.net.URL;
import java.util.ResourceBundle;

public class Menubar implements Initializable {
    private final CategoryController categoryController = CategoryController.getInstance();
    private final MenuController menuController = MenuController.getInstance();
    public Menu categoryMenu;
    public Menu offMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addMenuItems();
    }

    private void addMenuItems() {
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

    private void addOffMenuItems() {
        for (String mainCategory : categoryController.getDiscountedMainCategories()) {
            MenuItem menuItem = new MenuItem(mainCategory);
            menuItem.setOnAction(e -> goToDiscountedCategory(mainCategory));
            offMenu.getItems().add(menuItem);
        }
    }

    public void goToMainMenu() {
        menuController.goToMenu(MainMenu.getFxmlFilePath());
    }

    private void goToCategory(String mainCategory) {
        categoryController.setActiveCategoryByName(mainCategory);
        categoryController.changeIsOffMenuToFalse();
        menuController.goToMenu(ProductsMenu.getFxmlFilePath());
    }

    private void goToDiscountedCategory(String mainCategory) {
        categoryController.setActiveCategoryByName(mainCategory);
        categoryController.changeIsOffMenuToTrue();
        menuController.goToMenu(OffMenu.getFxmlFilePath());
    }
}
