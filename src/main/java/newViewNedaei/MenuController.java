package newViewNedaei;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {
    private static final MenuController instance = new MenuController();
    private Pane backgroundPane;
    private Pane currentPane;
    private String currentFxmlFilePath;
    private Pane panel;

    private MenuController() {
        try {
            backgroundPane = FXMLLoader.load(getClass().getResource("/BackgroundPane.fxml"));
            goToMenu(MainMenu.getFxmlFilePath());
        } catch (IOException ignored) {

        }
    }

    public static MenuController getInstance() {
        return instance;
    }

    public Pane getBackgroundPane() {
        return backgroundPane;
    }

    public void goToMenu(String fxmlFilePath) {
        try {
            currentFxmlFilePath = fxmlFilePath;
            Pane pane = FXMLLoader.load(getClass().getResource(currentFxmlFilePath));
            backgroundPane.getChildren().remove(currentPane);
            pane.setTranslateX(0);
            pane.setTranslateY(55);
            pane.setStyle("-fx-background-color: transparent");
            backgroundPane.getChildren().add(pane);
            currentPane = pane;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToPanel(String fxmlFilePath) {
        try {
            panel = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            panel.setTranslateX(300);
            panel.setTranslateY(155);
            panel.setStyle("-fx-background-color: royalblue");
            currentPane.setDisable(true);
            backgroundPane.getChildren().add(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enableCurrentPane() {
        backgroundPane.getChildren().remove(panel);
        goToMenu(currentFxmlFilePath);
    }
}
