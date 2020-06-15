package newViewNedaei.background;

import newViewNedaei.MenuController;

public class TopPane {

    public static String getFxmlFilePath() {
        return "/TopPane.fxml";
    }

    public void goBack() {
        MenuController.getInstance().goBack();
    }
}
