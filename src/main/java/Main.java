import controller.userControllers.UserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Market;
import newViewHatami.CreateAdminPanel;
import newViewNedaei.MenuController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(MenuController.getInstance().getBackgroundPane());
        MenuController.getInstance().setWindow(primaryStage);
        primaryStage.setTitle("my market");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Market.getInstance().initialize();
        if (Market.getInstance().getAllUsers().isEmpty()) {
            getFirstAdmin();
        }
        launch(args);
    }

    private static void getFirstAdmin() {
        MenuController.getInstance().goToPanel(CreateAdminPanel.getFxmlFilePath());
    }
}