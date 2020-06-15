package newViewNedaei;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import newViewNedaei.background.BackgroundPane;
import newViewNedaei.background.TopPane;

import java.io.IOException;
import java.util.Stack;

public class MenuController {
    private static final MenuController instance = new MenuController();
    private Pane backgroundPane;
    private Pane topPane;
    private Pane currentPane;
    private String currentFxmlFilePath;
    private Pane panel;
    private Stack<String> menuFxmlFilePaths;

    private MenuController() {
        try {
            menuFxmlFilePaths = new Stack<>();
            topPane = FXMLLoader.load(getClass().getResource(TopPane.getFxmlFilePath()));
            backgroundPane = FXMLLoader.load(getClass().getResource(BackgroundPane.getFxmlFilePath()));
            backgroundPane.getChildren().add(topPane);
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
            menuFxmlFilePaths.push(currentFxmlFilePath);
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
            topPane.setDisable(true);
            currentPane.setDisable(true);
            backgroundPane.getChildren().add(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enableCurrentPane() {
        backgroundPane.getChildren().remove(panel);
        topPane.setDisable(false);
        goToMenu(currentFxmlFilePath);
        menuFxmlFilePaths.pop();
    }

    public void removeCurrentPanel() {
        backgroundPane.getChildren().remove(panel);
    }

    public void goBack() {
        if (menuFxmlFilePaths.size() > 1) {
            menuFxmlFilePaths.pop();
            goToMenu(menuFxmlFilePaths.peek());
            menuFxmlFilePaths.pop();
        }
    }
}
