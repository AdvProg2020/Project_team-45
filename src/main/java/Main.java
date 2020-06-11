import graphicview.nedaei.MenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(MenuController.getInstance().getBackgroundPane());
        primaryStage.setTitle("my market");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
//        MenuManagement.run();
        launch(args);
    }
}