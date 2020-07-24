package client.newViewBagheri;

import client.controller.userControllers.SupporterController;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class SupporterChatsMenu {
    private final SupporterController supporterController = SupporterController.getInstance();
    public TabPane allChatsTabPain;

    public static String getFxmlFilePath() {
        return "/SupporterChatsMenu.fxml";
    }

    @FXML
    public void initialize() {
        addAllActiveChats();
        
    }

    public void addAllActiveChats() {
        for (ArrayList<String> chat : supporterController.getActiveSupporterAllActiveChats()) {
            allChatsTabPain.getTabs().add(creatChatTab(chat));
        }
    }

    public Tab creatChatTab(ArrayList<String> chatMassages) {
        Tab newChatTab = new Tab();
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();
        for (String massage : chatMassages) {
            vBox.getChildren().add(new Text(massage));
        }
        scrollPane.setContent(vBox);
        newChatTab.setContent(scrollPane);
        return newChatTab;
    }
}
