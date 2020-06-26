package newViewBagheri;

import controller.CategoryController;
import controller.FilteringController;
import controller.ProductController;
import controller.SortingController;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import newViewHatami.ValidatorField;
import newViewNedaei.MenuController;

import java.net.URL;
import java.util.*;

public class ProductsMenu implements Initializable {
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
        return "/ProductsMenu.fxml";
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
        for (String subcategoryName : categoryController.getActiveCategorySubcategories()) {
            Label label = new Label(subcategoryName);
            label.setOnMouseClicked(e -> goToCategory(subcategoryName));
            subcategoriesList.getChildren().add(label);
        }
    }

    private void addCompaniesNameList() {
        addCheckBoxListToVBox(categoryController.getActiveCategoryCompanies(), companiesNameList, "companyName");
    }

    private void addSellersNameList() {
        addCheckBoxListToVBox(categoryController.getActiveCategorySellers(), sellersUsernameList, "sellerUsername");
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
        categoryController.changeIsOffMenuToFalse();
        menuController.goToMenu(ProductsMenu.getFxmlFilePath());
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
        ArrayList<HashMap<String, String>> productInfosList = categoryController.getActiveCategoryProductInfosList();
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
        productInfoVBox.setPrefHeight(350.0);
        ImageView productImageView = new ImageView(new Image(productInfo.get("imageAddress")));
        productImageView.setOnMouseClicked(e -> goToProduct(productInfo.get("id")));
        productImageView.setPreserveRatio(true);
        productImageView.setFitWidth(190.0);
        productImageView.setFitHeight(250.0);
        BorderPane imagePane = new BorderPane(productImageView);
        BorderPane.setAlignment(productImageView, Pos.CENTER);
        imagePane.setPrefWidth(sizePrefWidth);
        imagePane.setPrefHeight(250.0);
        // TODO: productImageView.setFitWidth();
        // TODO: add pane and centering image
        int labelSize = 30;
        Label productName = new Label(productInfo.get("name"));
        productName.setOnMouseClicked(e -> goToProduct(productInfo.get("id")));
        setLabelStyle(productName, sizePrefWidth, labelSize);
        Label productScore = new Label("score: " + productInfo.get("averageScore") + " out 0f 5");
        setLabelStyle(productScore, sizePrefWidth, labelSize);
        String productPrice = productInfo.get("price");
        Label productPriceLabel = new Label();
        setLabelStyle(productPriceLabel, sizePrefWidth, labelSize);
        if (productPrice.equals("unavailable")) {
            productPriceLabel.setText(productPrice);
        } else {
            productPriceLabel.setText("price: " + productPrice);
        }
        productInfoVBox.getChildren().addAll(imagePane, productName, productScore, productPriceLabel);
        return productInfoVBox;
    }

    private void setLabelStyle(Label label, int prefWidth, int prefHeight) {
        label.setPrefWidth(prefWidth);
        label.setPrefHeight(prefHeight);
        label.setAlignment(Pos.CENTER);
    }

    private void goToProduct(String productId) {
        productController.setActiveProductBYProductIdForCategory(productId);
        menuController.goToMenu(ProductMenu.getFxmlFilePath());
    }
}