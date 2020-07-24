package client.newViewBagheri;

import client.controller.AuctionController;
import client.newViewHatami.ValidatorField;
import client.newViewNedaei.Panel;
import javafx.scene.control.Label;

public class ParticipateAuctionPanel extends Panel {
    private final AuctionController auctionController = AuctionController.getInstance();
    public ValidatorField proposedPrice;
    public Label massageLabel;

    public static String getFxmlFilePath() {
        return "/ParticipateAuctionPanel.fxml";
    }

    public void submitProposedPrice() {
        if (proposedPrice.validate()) {
//            try {
////                auctionController.recordProposedPrice(proposedPrice.getText());
//                goBack();
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
        }
    }
}