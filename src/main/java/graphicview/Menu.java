package graphicview;

import controller.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public abstract class Menu {
    protected String fxmlFilePath;

    protected void goToNextMenu(String fxmlFilePath) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            MenuController.getInstance().setCurrentPane(pane);
        } catch (IOException ignored) {

        }
    }
}
