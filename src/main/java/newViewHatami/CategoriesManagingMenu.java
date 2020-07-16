package newViewHatami;

import controller.CategoryController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import newViewNedaei.MenuController;

import java.util.ArrayList;

public class CategoriesManagingMenu {

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
        ArrayList<TreeItem<String>> stack = new ArrayList<>();
        ArrayList<String> categoriesTree = CategoryController.getInstance().getCategoriesTree();
        TreeItem<String> rootNode = new TreeItem<String>("categories");
        for (String categoryNode : categoriesTree) {
            int depth = Integer.parseInt(categoryNode.split(":")[0]);
            String categoryName = categoryNode.split(":")[1];
            TreeItem<String> treeNode = new TreeItem<>(categoryName);
            if (stack.size() <= depth)
                stack.add(treeNode);
            else
                stack.set(depth, treeNode);
            if (depth == 0)
                rootNode.getChildren().add(treeNode);
            else
                stack.get(depth-1).getChildren().add(treeNode);
        }
        categoriesTreeView.setRoot(rootNode);
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
