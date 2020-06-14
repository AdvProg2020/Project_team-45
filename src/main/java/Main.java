import controller.DatabaseController;
import controller.userControllers.UserController;
import model.Market;
import newViewNedaei.MenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(MenuController.getInstance().getBackgroundPane());
        primaryStage.setTitle("my market");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Market.getInstance().initialize();
        UserController.getInstance().setActiveUserByUsername("s");
        UserController.setLoggedIn(true);
        launch(args);
    }
}