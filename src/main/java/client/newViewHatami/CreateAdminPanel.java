package client.newViewHatami;

import client.controller.userControllers.AllUsersController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class CreateAdminPanel extends Panel {
    public Button createAdminButton;
    public Button closeButton;

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
        if (AllUsersController.getInstance().noAdmin()) {
            closeButton.setVisible(false);
        }
    }

    public void closeAdminCreatorPanel() {
        goBack();
    }
}
