package newViewNedaei.user.seller;

import controller.userControllers.UserController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.product.Product;
import model.user.Seller;

public class ProductsManagingMenu {
    @FXML
    private GridPane mainPane;

    public static String getFxmlFilePath() {
        return "/ProductsManagingMenu.fxml";
    }

    @FXML
    public void initialize() {
//        Seller seller = (Seller) UserController.getActiveUser();
//        int i = 0;
//        for (Product product : seller.getAvailableProducts().keySet()) {
//            mainPane.add(createProductDisplay(product), i%5, i/5);
//            i++;
//        }
    }

    private Pane createProductDisplay(Product product) {
        Label name = new Label();
        name.setPrefWidth(180);
        name.setPrefHeight(50);
        name.setAlignment(Pos.CENTER);
        name.setText(product.getName());
        name.setTranslateX(0);
        name.setTranslateY(0);

        Label id = new Label();
        id.setPrefWidth(180);
        id.setPrefHeight(50);
        id.setAlignment(Pos.CENTER);
        id.setText("id: " + product.getId());
        id.setTranslateX(0);
        id.setTranslateY(60);

        Button view = new Button("View");
        view.setPrefWidth(90);
        view.setPrefHeight(50);
        view.setTranslateX(0);
        view.setTranslateY(120);
//        view.setOnMouseClicked(event -> );

        Button edit = new Button("Edit");
        edit.setPrefWidth(90);
        edit.setPrefHeight(50);
        edit.setTranslateX(90);
        edit.setTranslateY(120);
//        view.setOnMouseClicked(event -> );

        Pane pane = new Pane();
        pane.getChildren().add(name);
        pane.getChildren().add(id);
        pane.getChildren().add(view);
        pane.getChildren().add(edit);

        return pane;
    }
}
