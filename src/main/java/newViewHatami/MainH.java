package newViewHatami;

import controller.userControllers.UserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Market;
import newViewNedaei.MenuController;

public class MainH extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(MenuController.getInstance().getBackgroundPane());
        MenuController.getInstance().goToMenu(UsersManagingMenu.getFxmlFilePath());
        primaryStage.setTitle("my market");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
//        MenuManagement.run();
        Market.getInstance().initialize();
        UserController.getInstance().setActiveUserByUsername("a");
        launch(args);
    }
}