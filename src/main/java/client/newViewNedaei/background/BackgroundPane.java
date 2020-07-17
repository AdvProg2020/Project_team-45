package client.newViewNedaei.background;

import client.newViewNedaei.MenuController;

public class BackgroundPane {

    public static String getFxmlFilePath() {
        return "/BackgroundPane.fxml";
    }

    public void goBack() {
        MenuController.getInstance().goBack();
    }
}
