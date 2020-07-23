package client.newViewBagheri;

import client.controller.userControllers.SupporterController;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

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
        for (ArrayList<String> chat : supporterController.getActiveUserAllActiveChats()) {
            
        }
    }
}
