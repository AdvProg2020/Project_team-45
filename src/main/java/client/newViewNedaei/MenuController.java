package client.newViewNedaei;

import client.newViewNedaei.background.BackgroundPane;
import client.newViewNedaei.background.TopPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

public class MenuController {
    private static final MenuController instance = new MenuController();
    private Pane backgroundPane;
    private Pane topPane;
    private Pane currentPane;
    private String currentFxmlFilePath;
    private Stack<Pane> panels;
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
            panels = new Stack<>();
        } catch (IOException ignored) {
            System.out.println(".......................................................");
            ignored.printStackTrace();
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
            if (!fxmlFilePath.equals(MainMenu.getFxmlFilePath())) {
                MainMenu.deInitialize();

            }
            menuFxmlFilePaths.push(currentFxmlFilePath);
            Pane pane = FXMLLoader.load(getClass().getResource(currentFxmlFilePath));
            backgroundPane.getChildren().remove(currentPane);
            pane.setTranslateX(0);
            pane.setTranslateY(55);
            pane.setStyle("-fx-background-color: transparent");
            backgroundPane.getChildren().add(pane);
            currentPane = pane;
            backgroundPane.getChildren().remove(topPane);
            topPane = FXMLLoader.load(getClass().getResource(TopPane.getFxmlFilePath()));
            backgroundPane.getChildren().add(topPane);
            SoundPlayer.playBackground((new Random()).nextInt(3) + 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToPanel(String fxmlFilePath) {
        try {
            Pane panel = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            panel.setTranslateX((1000 - panel.getPrefWidth())/2);
            panel.setTranslateY(55 + (600 - panel.getPrefHeight())/2);
            panel.setStyle("-fx-background-color: royalblue");
            topPane.setDisable(true);
            currentPane.setDisable(true);
            if (!panels.isEmpty())
                panels.lastElement().setDisable(true);
            backgroundPane.getChildren().add(panel);
            panels.push(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enableCurrentPane() {
        backgroundPane.getChildren().remove(panels.pop());
        if (panels.isEmpty()) {
            goToMenu(currentFxmlFilePath);
            topPane.setDisable(false);
        }
        else
            panels.lastElement().setDisable(false);
        menuFxmlFilePaths.pop();
    }

    public void removeCurrentPanel() {
        backgroundPane.getChildren().remove(panels.pop());
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
        fileChooser.setTitle("Open");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File avatarFile = fileChooser.showOpenDialog(MenuController.getInstance().getWindow());
        if (avatarFile != null)
            return copyFile(avatarFile);
        return null;
    }

    public String pickVideo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open video");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("mkv", "*.mkv"),
                new FileChooser.ExtensionFilter("mp4", "*.mp4")
        );
        File videoFile = fileChooser.showOpenDialog(MenuController.getInstance().getWindow());
        if (videoFile != null)
            return copyFile(videoFile);
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

    public Pane getTopPane() {
        return topPane;
    }
}
