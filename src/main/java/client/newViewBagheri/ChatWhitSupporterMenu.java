package client.newViewBagheri;

import client.controller.userControllers.SupporterController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ChatWhitSupporterMenu {
    private final SupporterController supporterController = SupporterController.getInstance();
    public VBox onlineSupporterListBox;
    public BorderPane chatPane;
    public VBox allMassagesBox;
    public TextArea newMassageTextArea;
    private int massageNumber;

    public static String getFxmlFilePath() {
        return "/ChatWhitSupporterMenu.fxml";
    }

    @FXML
    public void initialize() {
        chatPane.setVisible(false);
        addOnlineSupporters();
        massageNumber = 0;
        startUpdatingChat();
    }

    private void addOnlineSupporters() {
        for (String supporter : supporterController.getOnlineSupporters()) {
            Label supporterLabel = new Label(supporter);
            supporterLabel.setOnMouseClicked(e -> startNewChat(supporter));
            onlineSupporterListBox.getChildren().add(supporterLabel);
        }
    }

    private void startUpdatingChat() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    updateChat();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void startNewChat(String supporterUsername) {
        chatPane.setVisible(true);
        allMassagesBox.getChildren().clear();
        supporterController.startNewChatForActiveBuyer(supporterUsername);
        massageNumber = 0;
    }

    public void sendNewMassage() {
        String massageText = newMassageTextArea.getText();
        supporterController.addMassageForBuyer(massageText);
        newMassageTextArea.clear();
        updateChat();
    }

    private void updateChat() {
        ArrayList<String> messages = SupporterController.getInstance().getMessages();
        if (messages != null) {
            int size = messages.size();
            for (String message : messages.subList(massageNumber, size)) {
                Platform.runLater(() -> allMassagesBox.getChildren().add(new Text(message)));
            }
            massageNumber = size;
        }
    }
}
