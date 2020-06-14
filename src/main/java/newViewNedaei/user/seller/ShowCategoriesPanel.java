package newViewNedaei.user.seller;

import newViewNedaei.MenuController;

public class ShowCategoriesPanel {

    public static String getFxmlFilePath() {
        return "/ShowCategoriesPanel.fxml";
    }

    public void goBack() {
        MenuController.getInstance().enableCurrentPane();
    }
}
