package graphicview.nedaei;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {
    private static final MenuController instance = new MenuController();
    private Pane backgroundPane;
    private Pane currentPane;

    private MenuController() {
        try {
            backgroundPane = FXMLLoader.load(getClass().getResource("/BackgroundPane.fxml"));
            Pane pane = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
            setCurrentPane(pane);
        } catch (IOException ignored) {

        }
    }

    public static MenuController getInstance() {
        return instance;
    }

    public Pane getBackgroundPane() {
        return backgroundPane;
    }

    public void setCurrentPane(Pane pane) {
        backgroundPane.getChildren().remove(currentPane);
        pane.setTranslateX(0);
        pane.setTranslateY(55);
        backgroundPane.getChildren().add(pane);
        currentPane = pane;
    }
}
