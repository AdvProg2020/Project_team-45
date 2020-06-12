package newViewHatami;

import graphicview.nedaei.MenuController;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.Market;
import model.category.Category;
import model.category.ParentCategory;

import java.util.ArrayList;

public class CategoriesManagingMenu extends AppMenu {

    public TreeView categoriesTreeView;
    public Label errorLabel;

    public static String getFxmlFilePath() {
        return "/CategoriesManagingMenu.fxml";
    }

    public void fillTreeList() {
        ArrayList<Category> mainCategories = Market.getInstance().getMainCategories();
        TreeItem<String> rootNode = new TreeItem<String>("categories");
        for (Category mainCategory : mainCategories) {
            System.out.println(mainCategory.getName());
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
        // TODO
    }

    public void createNewCategory() {
        // TODO
    }

    public void back() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
    }
}
