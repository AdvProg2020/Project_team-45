package client.newViewBagheri;

import client.controller.userControllers.SupporterController;
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
        addAllActiveChats();
        
    }

    public void addAllActiveChats() {
        for (Map.Entry<String, ArrayList<String>> activeChat :
                supporterController.getActiveSupporterAllActiveChats().entrySet()) {
            allChatsTabPain.getTabs().add(creatChatTab(activeChat.getKey(), activeChat.getValue()));
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
        sendButton.setOnAction(e -> sendNewMassage(buyerUsername, textArea.getText()));
        chatVBox.getChildren().addAll(scrollPane, textArea, sendButton);
        newChatTab.setContent(chatVBox);
        addChatTOList(buyerUsername, newChatTab, massagesVBox);
        return newChatTab;
    }

    private void sendNewMassage(String username, String massageContent) {
        supporterController.addMassageForSupporter(username, massageContent);
        addMassageToChat(username, massageContent);
    }

    private void addMassageToChat(String massage, String username) {
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
}