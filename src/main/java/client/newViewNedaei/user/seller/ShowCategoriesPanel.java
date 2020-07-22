package client.newViewNedaei.user.seller;

import client.newViewNedaei.MenuController;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;

// todo: nedaei, ask hatami
public class ShowCategoriesPanel {
    public TreeView<String> treeView;

    public static String getFxmlFilePath() {
        return "/ShowCategoriesPanel.fxml";
    }

    public void goBack() {
        MenuController.getInstance().enableCurrentPane();
    }

//    @FXML
//    public void initialize() {
//        ArrayList<Category> mainCategories = Market.getInstance().getMainCategories();
//        TreeItem<String> rootNode = new TreeItem<>("categories");
//        for (Category mainCategory : mainCategories) {
//            TreeItem<String> mainNode = new TreeItem<>(mainCategory.getName());
//            addChildrenToNode(mainNode, mainCategory);
//            rootNode.getChildren().add(mainNode);
//        }
//        treeView.setRoot(rootNode);
//    }
//
//    private void addChildrenToNode(TreeItem<String> rootNode, Category category) {
//        if (category.isFinal())
//            return;
//        rootNode.setExpanded(true);
//        ArrayList<Category> subCategories = ((ParentCategory) category).getSubcategories();
//        for (Category subCategory : subCategories) {
//            TreeItem<String> node = new TreeItem<>(subCategory.getName());
//            addChildrenToNode(node, subCategory);
//            rootNode.getChildren().add(node);
//        }
//    }
}
