package client;

import client.newViewNedaei.MenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(MenuController.getInstance().getBackgroundPane());
        MenuController.getInstance().setWindow(primaryStage);
        primaryStage.setTitle("my market");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    @Override
//    public void stop() throws Exception {
//        DatabaseController.getInstance().writeToDatabase();
//    }

    public static void main(String[] args) {
//        Market.getInstance().initialize();
//        if (Market.getInstance().getAllUsers().isEmpty()) {
//            getFirstAdmin();
//        }
        launch(args);
    }

//    private static void getFirstAdmin() {
//        MenuController.getInstance().goToPanel(CreateAdminPanel.getFxmlFilePath());
//    }
}
