package client.newViewBagheri;

import client.newViewNedaei.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SupporterMenu {
    public AnchorPane mainPane;

    public static String getFxmlFilePath() {
        return "/SupporterMenu.fxml";
    }

    @FXML
    public void initialize() {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/PersonalInfoPane.fxml"));
            pane.setTranslateX(0);
            pane.setTranslateY(0);
            mainPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToChatsMenu(MouseEvent mouseEvent) {
        MenuController.getInstance().goToMenu(SupporterChatsMenu.getFxmlFilePath());
    }
}
