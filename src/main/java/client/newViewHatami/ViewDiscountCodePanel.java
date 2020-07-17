package client.newViewHatami;

import client.newViewNedaei.MenuController;
import client.newViewNedaei.Panel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import server.controller.CodedDiscountController;
import server.controller.userControllers.AllUsersController;

import java.util.HashMap;


public class ViewDiscountCodePanel extends Panel {

    public Label codeLabel;
    public Button viewOwnerButton;
    public Label percentageLabel;
    public Label startDateLabel;
    public Label endDateLabel;

    private String ownerUsername;

    private static String viewingDiscountCode;

    public static void setViewingDiscountCode(String code) {
        viewingDiscountCode = code;
    }

    public static String getFxmlPath() {
        return "/ViewDiscountCodePanel.fxml";
    }

    @FXML
    public void initialize() {
        HashMap<String, String> discountInfo = CodedDiscountController.getInstance().getDetailsHashMap(viewingDiscountCode);
        codeLabel.setText(discountInfo.get("code"));
        percentageLabel.setText(discountInfo.get("percentage"));
        startDateLabel.setText(discountInfo.get("startDate"));
        endDateLabel.setText(discountInfo.get("endDate"));
        ownerUsername = AllUsersController.getInstance().getUsernameById(discountInfo.get("owner"));
    }

    @Override
    public void goBack() {
        viewingDiscountCode = null;
        super.goBack();
    }

    public void viewOwner() {
        ViewUserPanel.setSelectedUsername(ownerUsername);
        System.out.println(ownerUsername);
        MenuController.getInstance().goToPanel(ViewUserPanel.getFxmlFilePath());
    }
}
