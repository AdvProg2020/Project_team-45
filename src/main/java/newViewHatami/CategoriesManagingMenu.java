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

    public TreeView<String> categoriesTreeView;
    public Label errorLabel;
    private static String selectedCategoryName;

    public static String getFxmlFilePath() {
        return "/CategoriesManagingMenu.fxml";
    }

    @FXML
    public void initialize(){
        fillTreeList();
        selectedCategoryName = null;
    }

    public void fillTreeList() {
        ArrayList<Category> mainCategories = Market.getInstance().getMainCategories();
        TreeItem<String> rootNode = new TreeItem<String>("categories");
        for (Category mainCategory : mainCategories) {
            TreeItem<String> mainNode = new TreeItem<String>(mainCategory.getName());
            addSubcategoriesToCategory(mainNode, mainCategory);
            rootNode.getChildren().add(mainNode);
        }
        categoriesTreeView.setRoot(rootNode);
    }


    private void addSubcategoriesToCategory(TreeItem<String> rootNode, Category category) {
        if (category.isFinal())
            return;
        rootNode.setExpanded(true);
        ArrayList<Category> subCategories = ((ParentCategory) category).getSubcategories();
        for (Category subCategory : subCategories) {
            TreeItem<String> node = new TreeItem<String>(subCategory.getName());
            addSubcategoriesToCategory(node, subCategory);
            rootNode.getChildren().add(node);
        }
    }

    public void editSelectedCategory() {
        setSelectedCategoryId();
        if (selectedCategoryName == null) {
            errorLabel.setText("no selected category");
            return;
        }
        setEditingCategory();
        MenuController.getInstance().goToPanel(EditCategoryPanel.getFxmlFilePath());
    }

    private void setEditingCategory() {
        System.out.println(CategoryController.getInstance().getCategoryId(selectedCategoryName));
        EditCategoryPanel.setEditingCategoryId(CategoryController.getInstance().getCategoryId(selectedCategoryName));
    }

    public void deleteSelectedCategory() {
        setSelectedCategoryId();
        if (selectedCategoryName == null) {
            errorLabel.setText("no selected category");
            return;
        }
        CategoryController.getInstance().deleteItemById(selectedCategoryName);
        fillTreeList();
        errorLabel.setText("category deleted successfully");
    }

    public void createNewCategory() {
        MenuController.getInstance().goToPanel(CreateCategoryPanel.getFxmlFilePath());
    }

    public void setSelectedCategoryId(){
        TreeItem<String> selectedItem = categoriesTreeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && !selectedItem.getValue().equals("categories"))
            selectedCategoryName = selectedItem.getValue();
    }

    public void back() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
    }
}
