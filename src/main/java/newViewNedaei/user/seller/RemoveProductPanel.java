package newViewNedaei.user.seller;

import newViewNedaei.MenuController;

public class RemoveProductPanel {

    public static String getFxmlFilePath() {
        return "/RemoveProductPanel.fxml";
    }

    public void goBack() {
        MenuController.getInstance().enableCurrentPane();
    }
}
