package client.newViewBagheri;

import client.newViewHatami.ValidatorField;
import client.newViewNedaei.MenuController;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import client.controller.CategoryController;
import client.controller.FilteringController;
import client.controller.ProductController;
import client.controller.SortingController;

import java.net.URL;
import java.util.*;

public class OffMenu implements Initializable {
    private final CategoryController categoryController = CategoryController.getInstance();
    private final FilteringController filteringController = FilteringController.getInstance();
    private final SortingController sortingController = SortingController.getInstance();
    private final ProductController productController = ProductController.getInstance();
    private final MenuController menuController = MenuController.getInstance();
    public Label activeCategoryNameLabel;
    public VBox subcategoriesList;
    public TextField productNameField;
    public VBox companiesNameList;
    public VBox sellersUsernameList;
    public CheckBox availableCheckBox;
    public VBox specialFeaturesListVBox;
    public ValidatorField minPriceField;
    public ValidatorField maxPriceField;
    public ChoiceBox sortingChoiceBox;
    public GridPane productsListPain;
    public HBox pageNumberVBox;

    public static String getFxmlFilePath() {
        return "/OffMenu.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filteringController.clearFilters();
        addActiveCategoryName();
        addCompaniesNameList();
        addSellersNameList();
        if (!categoryController.isActiveCategoryFinal()) {
            addSubcategoriesName();
            specialFeaturesListVBox.setVisible(false);
        } else {
            subcategoriesList.setVisible(false);
            addSpecialFeaturesList();
        }
        showProducts();
    }

    private void addActiveCategoryName() {
        activeCategoryNameLabel.setText("Active Category: " + categoryController.getActiveCategoryName());
    }

    private void addSubcategoriesName() {
        for (String subcategoryName : categoryController.getActiveCategoryDiscountedSubcategories()) {
            Label label = new Label(subcategoryName);
            label.setOnMouseClicked(e -> goToCategory(subcategoryName));
            subcategoriesList.getChildren().add(label);
        }
    }

    private void addCompaniesNameList() {
        addCheckBoxListToVBox(categoryController.getActiveCategoryDiscountedCompanies(), companiesNameList, "companyName");
    }

    private void addSellersNameList() {
        addCheckBoxListToVBox(categoryController.getActiveCategoryDiscountedSellers(), sellersUsernameList, "sellerUsername");
    }

    private void addSpecialFeaturesList() {
        LinkedHashMap<String, Set<String>> specialFeaturesList = categoryController.getActiveCategoryFeaturesAndValues();
        for (Map.Entry<String, Set<String>> specialFeature : specialFeaturesList.entrySet()) {
            VBox featureVBox = new VBox();
            featureVBox.getChildren().add(new Label(specialFeature.getKey() + ":"));
            ScrollPane valuesScrollPane = new ScrollPane();
            VBox valuesVBox = new VBox();
            addCheckBoxListToVBox(specialFeature.getValue(), valuesVBox, specialFeature.getKey());
            valuesScrollPane.setContent(valuesVBox);
            featureVBox.getChildren().add(valuesScrollPane);
        }
    }

    private void goToCategory(String categoryName) {
        categoryController.setActiveCategoryByName(categoryName);
        categoryController.changeIsOffMenuToTrue();
        menuController.goToMenu(OffMenu.getFxmlFilePath());
    }

    private void addCheckBoxListToVBox(Set<String> checkBoxTextList, VBox inputVBox, String type) {
//        Label clearLabel = new Label("clear");
//        clearLabel.setOnMouseClicked(e -> clearFilter(type, inputVBox));
        for (String checkBoxText : checkBoxTextList) {
            CheckBox checkBox = new CheckBox(checkBoxText);
            checkBox.setOnAction(e -> changeFilter(type, checkBox));
            inputVBox.getChildren().add(checkBox);
        }
    }

    public void changeFilter() {
        String productName = productNameField.getText();
        if (productName.isEmpty()) {
            filteringController.removeFilter("productName");
        } else {
            filteringController.addFilter("productName", productName);
        }
        showProducts();
    }

    private void changeFilter(String type, CheckBox input) {
        if (input.isSelected()) {
            filteringController.addFilter(type, input.getText());
        } else {
            filteringController.removeFilter(type, input.getText());
        }
        showProducts();
    }

    public void filteringByStock() {
        if (availableCheckBox.isSelected())
            filteringController.addFilter("available", "");
        else
            filteringController.removeFilter("available");
        showProducts();
    }

    public void submitPriceFilter() {
        addMinPriceFilter();
        addMaxPriceFilter();
        showProducts();
    }

    public void addMinPriceFilter() {
        if (minPriceField.validate()) {
            String minPrice = minPriceField.getText();
            if (!minPrice.isEmpty())
                filteringController.addFilter("minimumPrice", "" + minPriceField.getText());
        }
    }

    public void addMaxPriceFilter() {
        if (maxPriceField.validate()) {
            String maxPrice = maxPriceField.getText();
            if (!maxPrice.isEmpty())
                filteringController.addFilter("maximumPrice", "" + maxPrice);
        }
    }

    public void clearPriceFilter() {
        minPriceField.clear();
        minPriceField.setStyle("-fx-text-box-border: #039ed3;");
        maxPriceField.clear();
        maxPriceField.setStyle("-fx-text-box-border: #039ed3;");
        filteringController.removeFilter("minimumPrice");
        filteringController.removeFilter("maximumPrice");
        showProducts();
    }

//    private void clearFilter(String type, VBox inputVBox) {
//        filteringController.removeFilter(type);
//        inputVBox.getChildren().
//    }

    public void sortProductsList() {
        sortingController.setActiveSort((String) sortingChoiceBox.getValue());
        showProducts();
    }

    private void showProducts() {
        ArrayList<HashMap<String, String>> productInfosList = categoryController.getActiveCategoryDiscountedProductInfosList();
        createPageButton(productInfosList);
        changeProductsListPainProductInfos(productInfosList, 1);
    }

    private void createPageButton(ArrayList<HashMap<String, String>> productInfosList) {
        int pagesNumber = (productInfosList.size() + 19) / 20;
        if (pagesNumber > 1) {
            for (int i = 1; i <= pagesNumber; i++) {
                Button pageNumber = new Button("" + i);
                int finalI = i;
                pageNumber.setOnAction(e -> changeProductsListPainProductInfos(productInfosList, finalI));
                pageNumberVBox.getChildren().add(pageNumber);
            }
        }
    }

    private void changeProductsListPainProductInfos(ArrayList<HashMap<String, String>> productInfosList, int page) {
        productsListPain.getChildren().clear();
        int i = 0;
        for (HashMap<String, String> productInfo : productInfosList.subList((page - 1) * 20, Math.min(page * 20, productInfosList.size()))) {
            productsListPain.add(createProductInfoVBox(productInfo), i % 4, i / 4);
            i++;
        }
    }

    private VBox createProductInfoVBox(HashMap<String, String> productInfo) {
        VBox productInfoVBox = new VBox();
        productInfoVBox.setBorder(new Border(new BorderStroke(Color.BROWN,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        int sizePrefWidth = 195;
        productInfoVBox.setPrefWidth(sizePrefWidth);
        productInfoVBox.setPrefHeight(400.0);
        ImageView productImageView = new ImageView(new Image(productInfo.get("imageAddress")));
        productImageView.setOnMouseClicked(e -> goToProduct(productInfo.get("productId"), productInfo.get("sellInfoId")));
        productImageView.setPreserveRatio(true);
        productImageView.setFitWidth(190.0);
        productImageView.setFitHeight(250.0);
        BorderPane imagePane = new BorderPane(productImageView);
        BorderPane.setAlignment(productImageView, Pos.CENTER);
        imagePane.setPrefWidth(sizePrefWidth);
        imagePane.setPrefHeight(250.0);
        productInfoVBox.getChildren().add(imagePane);
        Label productName = new Label(productInfo.get("name"));
        productName.setOnMouseClicked(e -> goToProduct(productInfo.get("productId"), productInfo.get("sellInfoId")));
        setLabelStyle(productName, sizePrefWidth, 30);
        productInfoVBox.getChildren().add(productName);
        productInfoVBox.getChildren().add(createLabelForProductInfoVBox("score: " + productInfo.get("averageScore") + " out 0f 5"));
        productInfoVBox.getChildren().add(createLabelForProductInfoVBox("Start Time: " + productInfo.get("startTime")));
        productInfoVBox.getChildren().add(createLabelForProductInfoVBox("End Time: " + productInfo.get("endTime")));
        productInfoVBox.getChildren().add(createLabelForProductInfoVBox("Remaining Time: " + productInfo.get("remainingTime") + " hour"));
        productInfoVBox.getChildren().add(createLabelForProductInfoVBox("Original Price: " + productInfo.get("originalPrice")));
        productInfoVBox.getChildren().add(createLabelForProductInfoVBox("Discount Percent: " + productInfo.get("discountPercent")));
        productInfoVBox.getChildren().add(createLabelForProductInfoVBox("Final Price: " + productInfo.get("finalPrice")));
        return productInfoVBox;
    }

    private Label createLabelForProductInfoVBox(String text) {
        Label productScore = new Label(text);
        setLabelStyle(productScore, 195, 30);
        return productScore;
    }

    private void setLabelStyle(Label label, int prefWidth, int prefHeight) {
        label.setPrefWidth(prefWidth);
        label.setPrefHeight(prefHeight);
        label.setAlignment(Pos.CENTER);
    }

    private void goToProduct(String productId, String sellInfoId) {
        productController.setActiveProductBYProductIdForOff(productId, sellInfoId);
        menuController.goToMenu(ProductMenu.getFxmlFilePath());
    }
}