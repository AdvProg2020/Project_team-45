package client.newViewBagheri;

import client.controller.userControllers.SupporterController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ChatWhitSupporterMenu {
    private final SupporterController supporterController = SupporterController.getInstance();
    public VBox onlineSupporterListBox;
    public BorderPane chatPane;
    public VBox allMassagesBox;
    public TextArea newMassageTextArea;

    public static String getFxmlFilePath() {
        return "/ChatWhitSupporterMenu.fxml";
    }

    @FXML
    public void initialize() {
        chatPane.setVisible(false);
        addOnlineSupporters();
    }

    private void addOnlineSupporters() {
        for (String supporter : supporterController.getOnlineSupporter()) {
            Label supporterLabel = new Label(supporter);
            supporterLabel.setOnMouseClicked(e -> startNewChat(supporter));
            onlineSupporterListBox.getChildren().add(supporterLabel);
        }
    }

    private void startNewChat(String supporterUsername) {
        chatPane.setVisible(true);
        allMassagesBox.getChildren().clear();
        supporterController.startNewChatForActiveUser(supporterUsername);
    }

    public void sendNewMassage() {
        String massageText = newMassageTextArea.getText();
        supporterController.addMassageForBuyer(massageText);
        addMassageToChat(massageText);
    }

    private void addMassageToChat(String massage) {
        allMassagesBox.getChildren().add(new Text(massage));
    }
}
