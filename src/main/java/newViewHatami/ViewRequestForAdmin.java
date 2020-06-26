package newViewHatami;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Market;
import model.request.*;
import newViewNedaei.MenuController;
import newViewNedaei.Panel;

import java.util.HashMap;

public class ViewRequestForAdmin extends Panel {

    private static Request showingRequest;
    public GridPane sellerRegisterPane;
    public Label idLabel;
    public Label statusLabel;
    public Label typeLabel;
    public GridPane addOffPane;
    public GridPane addProductPane;
    public ListView<String> editListView;
    public Label editLabel;

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
        else if (showingRequest.getType().equals("add product") || showingRequest.getType().equals("remove product"))
            addProductPane.setVisible(true);
        else if (showingRequest.getType().equals("edit off")) {
            addOffPane.setVisible(true);
            editLabel.setVisible(true);
            setListView(showingRequest.getFieldsAndValues());
        }else if (showingRequest.getType().equals("edit product")) {
            addProductPane.setVisible(true);
            editLabel.setVisible(true);
            setListView(showingRequest.getFieldsAndValues());
        }
    }

    private void setListView(HashMap<String, String> fieldsAndValues) {
        ObservableList<String> items = FXCollections.observableArrayList ();
        for (String field : fieldsAndValues.keySet()) {
            String row = field + "->" + fieldsAndValues.get(field);
            items.add(row);
        }
        editListView.setItems(items);
        editListView.setVisible(true);
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
        else if (showingRequest.getType().equals("remove product"))
            sellerUsername = ((RemoveProductRequest) showingRequest).getSeller().getUsername();
        else {
            sellerUsername = ((ProductEditionRequest) showingRequest).getSeller().getUsername();
        }
        ViewUserPanel.setSelectedUsername(sellerUsername);
        MenuController.getInstance().goToPanel(ViewUserPanel.getFxmlFilePath());
    }

    public void viewOff() {
        if (showingRequest.getType().equals("add off"))
            ViewOffForAdmin.setViewingOff(((AddOffRequest) showingRequest).getOff());
        else
            ViewOffForAdmin.setViewingOff(Market.getInstance().getOffById(((OffEditionRequest) showingRequest).getOffId()));
        MenuController.getInstance().goToPanel(ViewOffForAdmin.getFxmlFilePath());
    }

    public void viewProduct() {
        if (showingRequest.getType().equals("add product"))
            ViewProductForAdmin.setShowingProductInfo(((AddProductRequest) showingRequest).getProductSellInfo());
        else if (showingRequest.getType().equals("remove product")){
            String productId =  ((RemoveProductRequest) showingRequest).getProductId();
            ViewProductForAdmin.setShowingProductInfo(((RemoveProductRequest) showingRequest).getSeller().getAvailableProductSellInfoById(productId));
        }else {
            String productId =  ((ProductEditionRequest) showingRequest).getProductId();
            ViewProductForAdmin.setShowingProductInfo(((ProductEditionRequest) showingRequest).getSeller().getAvailableProductSellInfoById(productId));
        }
        MenuController.getInstance().goToPanel(ViewProductForAdmin.getFxmlFilePath());
    }
}
