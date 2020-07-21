package client.newViewHatami;

import client.controller.userControllers.UserController;
import client.controller.userControllers.UsernameIsRequestException;
import client.newViewNedaei.MenuController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LoginRegisterMenu {
    public ToggleButton registerToggleButton;
    public ToggleButton loginToggleButton;

    public TextField loginUsernameField;
    public PasswordField loginPasswordField;

    public Label loginErrorLabel;

    private ToggleButton selectedToggle;
    public Pane loginPane;
    public Pane registerPane;

    public static String getFxmlFilePath() {
        return "/LoginRegisterMenu.fxml";
    }

    @FXML
    public void initialize() throws IOException {
        makeToggleGroup();
        registerPane.setVisible(false);
    }


    private void makeToggleGroup() {
        ToggleGroup toggleGroup = new ToggleGroup();
        registerToggleButton.setToggleGroup(toggleGroup);
        loginToggleButton.setToggleGroup(toggleGroup);
        toggleGroup.selectToggle(loginToggleButton);
        loginToggleButton.setSelected(true);
        selectedToggle = loginToggleButton;
        toggleGroup.selectedToggleProperty().addListener((ov, toggle, new_toggle) -> {
            if (loginToggleButton.isSelected()) {
                loginPane.setVisible(true);
                registerPane.setVisible(false);
                selectedToggle = loginToggleButton;
            } else if (registerToggleButton.isSelected()) {
                loginPane.setVisible(false);
                registerPane.setVisible(true);
                selectedToggle = registerToggleButton;
            } else
                selectedToggle.setSelected(true);
        });

    }

    public void doLogin() {
        loginErrorLabel.setTextFill(Color.PINK);
        if (loginUsernameField.getText().equals("")) {
            loginErrorLabel.setText("you missed username");
        } else if (loginPasswordField.getText().equals("")) {
            loginErrorLabel.setText("you missed password");
        } else {
            try {
                if (!UserController.getInstance().usernameExists(loginUsernameField.getText())) {
                    loginErrorLabel.setText("The username is invalid!");
                } else if (!UserController.getInstance().login(loginUsernameField.getText(), loginPasswordField.getText())) {
                    loginErrorLabel.setText("wrong password");
                } else {
                    loginErrorLabel.setText("login successful");
                    loginUsernameField.clear();
                    loginPasswordField.clear();
                    processLogin(loginUsernameField.getText());
                    // TODO : put address to go after login
                }
            } catch (UsernameIsRequestException e) {
                loginErrorLabel.setText("this username has a non-accepted register request");
            }
        }
    }

    private void processLogin(String username) {
        MenuController.getInstance().getTopPane().getChildren().get(2).setVisible(false);
        MenuController.getInstance().getTopPane().getChildren().get(6).setVisible(true);
        MenuController.getInstance().goBack();
    }
}
