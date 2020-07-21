package client;

import client.network.ClientSocket;
import client.newViewNedaei.MenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            ClientSocket.getInstance().connectToServer();
        } catch (IOException exception) {
            System.err.println("error in connecting to server");
        }
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
//        try {
//            ClientSocket.getInstance().connectToServer();
//        } catch (IOException exception) {
//            System.err.println("error in connecting to server");
//        }
//        try {
//            System.out.println(CategoryController.getInstance().testii("mammad"));
//        } catch (Throwable throwable) {
//            System.out.println("ma bordim!! " + throwable.getMessage());
//        }
    }




//    private static void getFirstAdmin() {
//        MenuController.getInstance().goToPanel(CreateAdminPanel.getFxmlFilePath());
//    }
}
