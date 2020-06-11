package newViewHatami;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AppMenu  {
    protected static Stage window;

    public static void setScene(Scene scene) {
        window.setScene(scene);
    }

    public static void setWindow(Stage window) {
        AppMenu.window = window;
    }
}
