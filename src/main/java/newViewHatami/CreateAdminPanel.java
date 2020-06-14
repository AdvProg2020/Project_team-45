package newViewHatami;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import newViewNedaei.MenuController;


public class CreateAdminPanel {
    public Button createAdminButton;

    public static String getFxmlFilePath() {
        return "/CreateAdminPanel.fxml";
    }

    public Pane registerPane;

    @FXML
    public void initialize() {
        Node roleSelectionChoiceBox = registerPane.getChildren().stream()
                .filter(x -> x.getId().equals("roleSelectionChoiceBox"))
                .findFirst().get();
        Node registerButton = registerPane.getChildren().stream()
                .filter(x -> x.getId().equals("registerButton"))
                .findFirst().get();

        Node createAdminButton = registerPane.getChildren().stream()
                .filter(x -> x.getId().equals("createAdminButton"))
                .findFirst().get();

        roleSelectionChoiceBox.setVisible(false);
        registerButton.setVisible(false);
        createAdminButton.setVisible(true);
    }

    public void closeAdminCreatorPanel() {
        MenuController.getInstance().goToMenu(AdminMenu.getFxmlFilePath());
        // TODO
    }
}
