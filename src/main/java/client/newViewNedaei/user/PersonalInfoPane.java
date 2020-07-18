package client.newViewNedaei.user;

import client.newViewHatami.Validator;
import client.newViewHatami.ValidatorField;
import client.controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;

public class PersonalInfoPane {
    public Label role;
    public ImageView view;
    public Label error;
    public Label username;
    public ValidatorField firstName;
    public ValidatorField lastName;
    public ValidatorField emailAddress;
    public ValidatorField phoneNumber;
    public ValidatorField password;

    public PersonalInfoPane() {

    }

    public static String getFxmlFilePath() {
        return "/PersonalInfoPane.fxml";
    }

    @FXML
    public void initialize() {
        role.setText(UserController.getInstance().getRole());
        HashMap<String, String> personalInfo = UserController.getInstance().getActiveUserPersonalInfo();
        username.setText(personalInfo.get("username"));
        firstName.setPromptText(personalInfo.get("firstName"));
        lastName.setPromptText(personalInfo.get("lastName"));
        emailAddress.setPromptText(personalInfo.get("emailAddress"));
        phoneNumber.setPromptText(personalInfo.get("phoneNumber"));
        password.setPromptText(personalInfo.get("password"));

//        Image image = null;
//        try {
//            image = new Image("/photos/poker.png");
//            String imageAddress = UserController.getActiveUser().getPersonalInfo().getAvatarPath();
//            image = new Image("/photos/" + imageAddress.substring(imageAddress.lastIndexOf("\\")+1));
//        } catch (Exception ignored) {
//
//        }
//        view.setImage(image);
    }

    public void editFirstName() {
        try {
            UserController.getInstance().setPersonalInfoField("firstName", firstName.getText());
            firstName.setPromptText(firstName.getText());
            error.setText("");
        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    public void editLastName() {
        try {
            UserController.getInstance().setPersonalInfoField("lastName", lastName.getText());
            lastName.setPromptText(lastName.getText());
            error.setText("");
        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    public void editEmail() {
        try {
            UserController.getInstance().setPersonalInfoField("emailAddress", emailAddress.getText());
            emailAddress.setPromptText(emailAddress.getText());
            error.setText("");
        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    public void editPhone() {
        try {
            UserController.getInstance().setPersonalInfoField("phoneNumber", phoneNumber.getText());
            phoneNumber.setPromptText(phoneNumber.getText());
            error.setText("");
        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    public void editPassword() {
        try {
            UserController.getInstance().setPersonalInfoField("password", password.getText());
            password.setPromptText(password.getText());
            error.setText("");
        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }
}
