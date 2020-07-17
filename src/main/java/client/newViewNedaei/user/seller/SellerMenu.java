package client.newViewNedaei.user.seller;

import client.newViewNedaei.MenuController;
import client.newViewNedaei.user.seller.off.AddOffPanel;
import client.newViewNedaei.user.seller.off.OffsManagingMenu;
import client.newViewNedaei.user.seller.product.AddProductPanel;
import client.newViewNedaei.user.seller.product.ProductsManagingMenu;
import client.newViewNedaei.user.seller.product.RemoveProductPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import server.controller.userControllers.SellerController;
import server.model.Company;

import java.io.IOException;

public class SellerMenu {
    @FXML
    private Label balance;
    @FXML
    private Label companyName;
    @FXML
    private Label companyInfo;
    @FXML
    private AnchorPane mainPane;

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

            Company company = SellerController.getInstance().getSellerCompany();
            companyName.setText(company.getName());
            companyInfo.setText(company.getOtherInformation());
            balance.setText("" + SellerController.getInstance().getSellerBalance());
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
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
}
