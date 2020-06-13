package newViewHatami;

import controller.CategoryController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.Market;
import model.category.Category;
import model.category.ParentCategory;
import newViewNedaei.MenuController;

import java.util.ArrayList;

public class CategoriesManagingMenu extends AppMenu {

    public TreeView categoriesTreeView;
    public Label errorLabel;

    public static String getFxmlFilePath() {
        return "/CategoriesManagingMenu.fxml";
    }

    @FXML
    public void initialize(){
        fillTreeList();
    }

    public void fillTreeList() {
        ArrayList<Category> mainCategories = Market.getInstance().getMainCategories();
        TreeItem<String> rootNode = new TreeItem<String>("categories");
        for (Category mainCategory : mainCategories) {
            TreeItem<String> mainNode = new TreeItem<String>(mainCategory.getName());
            addChildrenToNode(mainNode, mainCategory);
            rootNode.getChildren().add(mainNode);
        }
        categoriesTreeView.setRoot(rootNode);
    }

    private void addChildrenToNode(TreeItem<String> rootNode, Category category) {
        if (category.isFinal())
            return;
        rootNode.setExpanded(true);
        ArrayList<Category> subCategories = ((ParentCategory) category).getSubcategories();
        for (Category subCategory : subCategories) {
            TreeItem<String> node = new TreeItem<String>(subCategory.getName());
            addChildrenToNode(node, subCategory);
            rootNode.getChildren().add(node);
        }
    }

    public void editSelectedCategory() {
        // TODO
    }

    public void deleteSelectedCategory() {
        String selectedCategoryId = getSelectedCategoryId();
        CategoryController.getInstance().deleteItemById(selectedCategoryId);
        fillTreeList();
        errorLabel.setText("category deleted successfully");
    }

    public void createNewCategory() {
        // TODO
    }

    public String getSelectedCategoryId(){
        TreeItem<String> selectedItem = (TreeItem<String>) categoriesTreeView.getSelectionModel().getSelectedItem();
        return selectedItem.getValue();
    }

    public void back() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
    }
}
