package client;

import client.controller.userControllers.AllUsersController;
import client.network.ClientSocket;
import client.newViewHatami.CreateAdminPanel;
import client.newViewNedaei.MenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMain1 extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            ClientSocket.getInstance().connectToServer();
            ClientSocket.getInstance().start();
        } catch (IOException exception) {
            System.err.println("error in connecting to server");
        }
        Scene scene = new Scene(MenuController.getInstance().getBackgroundPane());
        MenuController.getInstance().setWindow(primaryStage);
        primaryStage.setTitle("my market");
        primaryStage.setScene(scene);
        primaryStage.show();
        if (AllUsersController.getInstance().noAdmin()) {
            getFirstAdmin();
        }
    }

//    @Override
//    public void stop() throws Exception {
//        DatabaseController.getInstance().writeToDatabase();
//    }

    public static void main(String[] args) {
//        Market.getInstance().initialize();


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

    @Override
    public void stop() throws Exception {
        ClientSocket.getInstance().closeSocket();
    }

    private static void getFirstAdmin() {
        MenuController.getInstance().goToPanel(CreateAdminPanel.getFxmlFilePath());
    }
}
