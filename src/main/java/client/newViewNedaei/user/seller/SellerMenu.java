package client.newViewNedaei.user.seller;

import client.controller.BankController;
import client.newViewBagheri.AddAuctionPanel;
import client.newViewHatami.Validator;
import client.newViewHatami.ValidatorField;
import client.newViewNedaei.MenuController;
import client.newViewNedaei.user.seller.off.AddOffPanel;
import client.newViewNedaei.user.seller.off.OffsManagingMenu;
import client.newViewNedaei.user.seller.product.AddProductPanel;
import client.newViewNedaei.user.seller.product.ProductsManagingMenu;
import client.newViewNedaei.user.seller.product.RemoveProductPanel;
import client.controller.userControllers.SellerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

// nedaei: turned to new format successfully!
public class SellerMenu {
    public Label balance;
    public Label companyName;
    public Label companyInfo;
    public AnchorPane mainPane;
    public ValidatorField charge;
    public ValidatorField deposit;
    public Label accountBalance;
    public Label error;

    public static String getFxmlFilePath() {
        return "/SellerMenu.fxml";
    }

    @FXML
    public void initialize() {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/PersonalInfoPane.fxml"));
            pane.setTranslateX(0);
            pane.setTranslateY(0);
            mainPane.getChildren().add(pane);

            HashMap<String, String> company = SellerController.getInstance().getSellerCompany();
            companyName.setText(company.get("name"));
            companyInfo.setText(company.get("info"));
            updateWalletAndAccount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateWalletAndAccount() {
        balance.setText("" + SellerController.getInstance().getSellerBalance());
        accountBalance.setText("" + SellerController.getInstance().getSellerAccountBalance());
    }

    public void manageProducts() {
        MenuController.getInstance().goToMenu(ProductsManagingMenu.getFxmlFilePath());
    }

    public void viewOffs() {
        MenuController.getInstance().goToMenu(OffsManagingMenu.getFxmlFilePath());
    }

    public void viewSales() {
        MenuController.getInstance().goToPanel(SalesHistoryPanel.getFxmlFilePath());
    }

    public void addProduct() {
        MenuController.getInstance().goToPanel(AddProductPanel.getFxmlFilePath());
    }

    public void removeProduct() {
        MenuController.getInstance().goToPanel(RemoveProductPanel.getFxmlFilePath());
    }

    public void showCategories() {
        MenuController.getInstance().goToPanel(ShowCategoriesPanel.getFxmlFilePath());
    }

    public void addOff() {
        MenuController.getInstance().goToPanel(AddOffPanel.getFxmlFilePath());
    }

    public void addAuction() {
        MenuController.getInstance().goToPanel(AddAuctionPanel.getFxmlFilePath());
    }

    public void chargeWallet(MouseEvent mouseEvent) {
        if (!charge.validate()) {
            error.setText("invalid charge format");
            return;
        }
        try {
            BankController.getInstance().chargeWallet(Integer.parseInt(charge.getText()));
            updateWalletAndAccount();
            error.setText("");
        } catch (Throwable e) {
            error.setText(e.getMessage());
        }
    }

    public void depositAccount(MouseEvent mouseEvent) {
        if (!deposit.validate()) {
            error.setText("invalid deposit format");
            return;
        }
        try {
            BankController.getInstance().depositAccount(Integer.parseInt(deposit.getText()));
            updateWalletAndAccount();
            error.setText("");
        } catch (Throwable e) {
            error.setText(e.getMessage());
        }
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }
}
