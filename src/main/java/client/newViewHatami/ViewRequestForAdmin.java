package client.newViewHatami;

import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import server.model.Comment;
import server.model.Market;
import server.model.product.Product;
import server.model.request.*;

import java.util.HashMap;

// TODO

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
    public Label commentText;
    public GridPane commentGridPane;
    public Label commentTitle;

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
        } else if (showingRequest.getType().equals("new comment")) {
            setUpCommentInfo();
            commentGridPane.setVisible(true);
        }
    }

    private void setUpCommentInfo() {
        Comment comment = ((CommentRequest) showingRequest).getComment();
        commentTitle.setText(comment.getTitle());
        commentText.setText(comment.getContent());
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
        else if (showingRequest.getType().equals("new comment"))
            sellerUsername = ((CommentRequest) showingRequest).getComment().getUser().getUsername();
        else {
            sellerUsername = ((ProductEditionRequest) showingRequest).getSeller().getUsername();
        }
        ViewUserPanel.setSelectedUsername(sellerUsername);
        MenuController.getInstance().goToPanel(ViewUserPanel.getFxmlFilePath());
    }

    public void viewOff() {
        if (showingRequest.getType().equals("add off"))
            ViewOffForAdmin.setViewingOff(((AddOffRequest) showingRequest).getOff().getId());
        else
            ViewOffForAdmin.setViewingOff(((OffEditionRequest) showingRequest).getOffId());
        MenuController.getInstance().goToPanel(ViewOffForAdmin.getFxmlFilePath());
    }

    public void viewProduct() {
        if (showingRequest.getType().equals("add product"))
            ViewProductForAdmin.setShowingProductInfo(((AddProductRequest) showingRequest).getProductSellInfo());
        else if (showingRequest.getType().equals("remove product")){
            String productId =  ((RemoveProductRequest) showingRequest).getProductId();
            ViewProductForAdmin.setShowingProductInfo(((RemoveProductRequest) showingRequest).getSeller().getAvailableProductSellInfoById(productId));
        }else if (showingRequest.getType().equals("new comment")){
            Product product =  ((CommentRequest) showingRequest).getComment().getProduct();
            ViewProductForAdmin.setShowingProductInfo(product.getDefaultSellInfo());
        }else {
            String productId =  ((ProductEditionRequest) showingRequest).getProductId();
            ViewProductForAdmin.setShowingProductInfo(((ProductEditionRequest) showingRequest).getSeller().getAvailableProductSellInfoById(productId));
        }
        MenuController.getInstance().goToPanel(ViewProductForAdmin.getFxmlFilePath());
    }
}
