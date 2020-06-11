package graphicview.nedaei;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenu extends Menu{
    private static final MainMenu instance = new MainMenu();
    @FXML
    private Button userAccount;
    @FXML
    private Button products;
    @FXML
    private Button offs;

    private MainMenu() {
        fxmlFilePath = "/MainMenu.fxml";
    }

    public static MainMenu getInstance() {
        return instance;
    }

    public void goToUserAccount() {

    }
}
