package client.newViewNedaei;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainMenu{

    public static String getFxmlFilePath() {
        return "/MainMenu.fxml";
    }

    @FXML
    public void initialize() {
        Platform.runLater(() ->
                MenuController.getInstance().getBackgroundPane().setStyle("-fx-background-color: royalblue"));
    }

    public static void deInitialize() {
        Platform.runLater(() ->
                MenuController.getInstance().getBackgroundPane().setStyle("-fx-background-color: rosybrown"));
    }

}
