package client.newViewBagheri;

import client.controller.AuctionController;
import client.newViewNedaei.MenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class AuctionMenu {
    private final MenuController menuController = MenuController.getInstance();
    private final AuctionController auctionController = AuctionController.getInstance();
    public Label productNameLabel;
    public Label basePriceLabel;
    public Label proposedPriceLabel;
    public Label endDateLabel;
    public VBox allMassagesBox;
    private int massageNumber;
    public TextArea newMassageTextArea;

    public static String getFxmlFilePath() {
        return "/AuctionMenu.fxml";
    }

    @FXML
    public void initialize() {
        massageNumber = 0;
        addAuctionInfo();
        addProposedPrice();
        addMassagesToChat();
    }

    private void addAuctionInfo() {
        HashMap<String, String> auctionInfo = auctionController.getActiveAuctionInfos();
        productNameLabel.setText(auctionInfo.get("productName"));
        basePriceLabel.setText("Base Product: " + auctionInfo.get("basePrice"));
        endDateLabel.setText("End Date: " + auctionInfo.get("endDate"));
    }

    private void addProposedPrice() {
        proposedPriceLabel.setText("Proposed Price: " + auctionController.getActiveUserProposedPrice());
    }
    
    private void addMassagesToChat() {
        ArrayList<String> allMassages = auctionController.getActiveAuctionAllMassages();
        int size = allMassages.size();
        for (String massage : allMassages.subList(massageNumber, size)) {
            allMassagesBox.getChildren().add(new Text(massage));
        }
        massageNumber = size;
    }

    public void goToParticipateAuctionPanel() {
        menuController.goToPanel(ParticipateAuctionPanel.getFxmlFilePath());
        addProposedPrice();
    }

    public void sendNewMassage() {
        auctionController.addMassageToActiveAuction(newMassageTextArea.getText());
    }
}