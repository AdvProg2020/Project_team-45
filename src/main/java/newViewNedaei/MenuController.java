package newViewNedaei;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import newViewNedaei.background.BackgroundPane;
import newViewNedaei.background.TopPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    private int resourceFileCounter = 0;

    private Stage window;

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

    public void setWindow(Stage window) {
        this.window = window;
    }

    public Stage getWindow() {
        return window;
    }

    public String pickPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File avatarFile = fileChooser.showOpenDialog(MenuController.getInstance().getWindow());
        if (avatarFile != null)
            return copyFile(avatarFile);
        return null;
    }

    public String copyFile(File copyingFile) {
        String format = copyingFile.getName().split("\\.")[1];
        File dest = new File("src/main/resources/photos/" + resourceFileCounter + "." +  format);
        try {
            do {
                resourceFileCounter++;
                dest = new File("src/main/resources/photos/" + resourceFileCounter + "." +  format);
            } while (!dest.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        resourceFileCounter++;
        try {
             FileInputStream fileInputStream = new FileInputStream(copyingFile);
             FileOutputStream fileOutputStream = new FileOutputStream(dest) ;

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileInputStream.read(buffer)) > 0) {

                fileOutputStream.write(buffer, 0, length);
            }
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest.getPath();
    }
}
