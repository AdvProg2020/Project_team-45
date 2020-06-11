package newViewHatami;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AppMenu.setWindow(primaryStage);
        primaryStage.setScene(UsersManagingMenuG.getScene());
        primaryStage.show();
    }
}
