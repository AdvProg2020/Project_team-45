package client.newViewHatami;

import client.controller.RequestController;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

// TODO

public class ViewRequestForAdmin extends Panel {

    private static String showingRequestId;

    public GridPane sellerRegisterPane;
    public Label idLabel;
    public Label statusLabel;
    public Label typeLabel;
    public GridPane addOffPane;
    public GridPane addProductPane;
    public ListView<String> editListView;
    public Label editLabel;
    public Label commentText;
    public GridPane commentGridPane;
    public Label commentTitle;

    public static String getFxmlFilePath() {
        return "/ViewRequestForAdmin.fxml";
    }

    @FXML
    public void initialize() {
        HashMap<String, String> requestInfo = RequestController.getInstance().getRequestInfo(showingRequestId);
        setDefaultFields(requestInfo);
        String requestType = String.valueOf(requestInfo.get("type"));
        switch (requestType) {
            case "seller register":
                sellerRegisterPane.setVisible(true);
                break;
            case "add off":
                addOffPane.setVisible(true);
                break;
            case "add product":
            case "remove product":
                addProductPane.setVisible(true);
                break;
            case "edit off":
                addOffPane.setVisible(true);
                editLabel.setVisible(true);
                setListView(RequestController.getInstance().getRequestFieldsAndValues(showingRequestId));
                break;
            case "edit product":
                addProductPane.setVisible(true);
                editLabel.setVisible(true);
                setListView(RequestController.getInstance().getRequestFieldsAndValues(showingRequestId));
                break;
            case "new comment":
                setUpCommentInfo();
                commentGridPane.setVisible(true);
                break;
        }
    }

    private void setUpCommentInfo() {
        HashMap<String, String> commentInfo = RequestController.getInstance().getRequestsCommentInfo(showingRequestId);
        commentTitle.setText(commentInfo.get("title"));
        commentText.setText(commentInfo.get("text"));
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

    private void setDefaultFields(HashMap<String, String> requestInfo) {
        idLabel.setText(showingRequestId);
        statusLabel.setText(requestInfo.get("status"));
        typeLabel.setText(requestInfo.get("type"));
    }

    public static void setShowingRequestId(String showingRequestId) {
        ViewRequestForAdmin.showingRequestId = showingRequestId;
    }

    public void viewSeller() {
        String sellerUsername = RequestController.getInstance().getSellerId(showingRequestId);
        ViewUserPanel.setSelectedUsername(sellerUsername);
        MenuController.getInstance().goToPanel(ViewUserPanel.getFxmlFilePath());
    }

    public void viewOff() {
        String showingOffId =  RequestController.getInstance().getOffId(showingRequestId);
        ViewOffForAdmin.setViewingOff(showingOffId);
        MenuController.getInstance().goToPanel(ViewOffForAdmin.getFxmlFilePath());
    }

    public void viewProduct() {
        String showingSellInfoId =  RequestController.getInstance().getProductId(showingRequestId);
        ViewProductForAdmin.setShowingProductInfo(showingSellInfoId);
        MenuController.getInstance().goToPanel(ViewProductForAdmin.getFxmlFilePath());
    }
}
