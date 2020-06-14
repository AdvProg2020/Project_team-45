package newViewNedaei.user.seller;

import javafx.fxml.FXML;
import javafx.scene.control.TreeView;
import newViewNedaei.MenuController;

public class ShowCategoriesPanel {
    @FXML
    private TreeView treeView;

    public static String getFxmlFilePath() {
        return "/ShowCategoriesPanel.fxml";
    }

    public void goBack() {
        MenuController.getInstance().enableCurrentPane();
    }
}
