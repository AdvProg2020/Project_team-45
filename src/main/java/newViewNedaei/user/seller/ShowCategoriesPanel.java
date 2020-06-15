package newViewNedaei.user.seller;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.Market;
import model.category.Category;
import model.category.ParentCategory;
import newViewNedaei.MenuController;

import java.util.ArrayList;

public class ShowCategoriesPanel {
    @FXML
    private TreeView<String> treeView;

    public static String getFxmlFilePath() {
        return "/ShowCategoriesPanel.fxml";
    }

    public void goBack() {
        MenuController.getInstance().enableCurrentPane();
    }

    @FXML
    public void initialize() {
        ArrayList<Category> mainCategories = Market.getInstance().getMainCategories();
        TreeItem<String> rootNode = new TreeItem<>("categories");
        for (Category mainCategory : mainCategories) {
            TreeItem<String> mainNode = new TreeItem<>(mainCategory.getName());
            addChildrenToNode(mainNode, mainCategory);
            rootNode.getChildren().add(mainNode);
        }
        treeView.setRoot(rootNode);
    }

    private void addChildrenToNode(TreeItem<String> rootNode, Category category) {
        if (category.isFinal())
            return;
        rootNode.setExpanded(true);
        ArrayList<Category> subCategories = ((ParentCategory) category).getSubcategories();
        for (Category subCategory : subCategories) {
            TreeItem<String> node = new TreeItem<>(subCategory.getName());
            addChildrenToNode(node, subCategory);
            rootNode.getChildren().add(node);
        }
    }
}
