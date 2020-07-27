package client.newViewBagheri;

import client.controller.userControllers.SupporterController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SupporterChatsMenu {
    private final SupporterController supporterController = SupporterController.getInstance();
    public TabPane allChatsTabPain;
    private HashMap<String, Tab> allTabsList;
    private HashMap<String, VBox> allVBoxMassagesList;

    public SupporterChatsMenu() {
    }

    public static String getFxmlFilePath() {
        return "/SupporterChatsMenu.fxml";
    }

    @FXML
    public void initialize() {
        allTabsList = new HashMap<>();
        allVBoxMassagesList = new HashMap<>();
        addAllActiveChats();
        startUpdatingChats();
    }

    public void addAllActiveChats() {
        for (Map.Entry<String, ArrayList<String>> activeChat :
                supporterController.getActiveSupporterAllActiveChats().entrySet()) {
            Platform.runLater(() -> allChatsTabPain.getTabs().add(creatChatTab(activeChat.getKey(), activeChat.getValue())));
        }
    }

    public Tab creatChatTab(String buyerUsername, ArrayList<String> chatMassages) {
        Tab newChatTab = new Tab(buyerUsername);
        VBox chatVBox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        VBox massagesVBox = new VBox();
        for (String massage : chatMassages) {
            massagesVBox.getChildren().add(new Text(massage));
        }
        scrollPane.setContent(massagesVBox);
        TextArea textArea = new TextArea();
        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendNewMassageFromSupporter(buyerUsername, textArea));
        chatVBox.getChildren().addAll(scrollPane, textArea, sendButton);
        newChatTab.setContent(chatVBox);
        addChatTOList(buyerUsername, newChatTab, massagesVBox);
        return newChatTab;
    }

    private void sendNewMassageFromSupporter(String buyerUsername, TextArea textArea) {
        sendNewMassage(buyerUsername, textArea.getText());
        textArea.clear();
    }

    private void sendNewMassage(String username, String massageContent) {
        supporterController.addMassageForSupporter(username, massageContent);
//        addMassageToChat(username, massageContent);
    }

    private void addMassageToChat(String username, String massage) {
        VBox massageVBox = allVBoxMassagesList.get(username);
        if (massageVBox == null) {
            ArrayList<String> massages = new ArrayList<>();
            massages.add(massage);
            allChatsTabPain.getTabs().add(creatChatTab(username, massages));
            massageVBox = allVBoxMassagesList.get(username);
        }
        massageVBox.getChildren().add(new Text(massage));
    }

    private void addChatTOList(String username, Tab chatTab, VBox massagesVBox) {
        allTabsList.put(username, chatTab);
        allVBoxMassagesList.put(username, massagesVBox);
    }

    public void removeChatTabFromList(String username) {
        allTabsList.remove(username);
    }

    private void startUpdatingChats() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> updateChats());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void updateChats() {
        if (supporterController.getActiveSupporterAllActiveChats() != null) {
            for (Map.Entry<String, ArrayList<String>> activeChat :
                    supporterController.getActiveSupporterAllActiveChats().entrySet()) {
                VBox vBox = allVBoxMassagesList.get(activeChat.getKey());
                if(vBox != null) {
                    vBox.getChildren().clear();
                    for (String message : activeChat.getValue()) {
                        vBox.getChildren().add(new Text(message));
                    }
                }
                else
                   allChatsTabPain.getTabs().add(creatChatTab(activeChat.getKey(), activeChat.getValue()));
            }
        }
    }
}