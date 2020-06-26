package newViewHatami;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Market;
import model.request.AddOffRequest;
import model.request.AddProductRequest;
import model.request.Request;
import model.request.SellerRegisterRequest;
import newViewNedaei.MenuController;
import newViewNedaei.Panel;

public class ViewRequestForAdmin extends Panel {

    private static Request showingRequest;
    public GridPane sellerRegisterPane;
    public Label idLabel;
    public Label statusLabel;
    public Label typeLabel;
    public GridPane addOffPane;
    public GridPane addProductPane;

    public static String getFxmlFilePath() {
        return "/ViewRequestForAdmin.fxml";
    }

    @FXML
    public void initialize() {
        setDefaultFields();
        if (showingRequest.getType().equals("seller register"))
            sellerRegisterPane.setVisible(true);
        else if (showingRequest.getType().equals("add off"))
            addOffPane.setVisible(true);
        else if (showingRequest.getType().equals("add product"))
            addProductPane.setVisible(true);
    }

    private void setDefaultFields() {
        idLabel.setText(showingRequest.getId());
        statusLabel.setText(showingRequest.getRequestStatus());
        typeLabel.setText(showingRequest.getType());
    }

    public static void setShowingRequest(String showingRequest) {
        ViewRequestForAdmin.showingRequest = Market.getInstance().getRequestById(showingRequest);
    }

    public void viewSeller() {
        String sellerUsername = "";
        if (showingRequest.getType().equals("seller register"))
            sellerUsername = ((SellerRegisterRequest) showingRequest).getSeller().getUsername();
        else if (showingRequest.getType().equals("add product"))
            sellerUsername = ((AddProductRequest) showingRequest).getSeller().getUsername();
        ViewUserPanel.setSelectedUsername(sellerUsername);
        MenuController.getInstance().goToPanel(ViewUserPanel.getFxmlFilePath());
    }

    public void viewOff() {
        ViewOffForAdmin.setViewingOff(((AddOffRequest) showingRequest).getOff());
        MenuController.getInstance().goToPanel(ViewOffForAdmin.getFxmlFilePath());
    }

    public void viewProduct() {
        ViewProductForAdmin.setShowingProductInfo(((AddProductRequest) showingRequest).getProductSellInfo());
        MenuController.getInstance().goToPanel(ViewProductForAdmin.getFxmlFilePath());
    }
}
