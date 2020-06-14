package newViewHatami;

import controller.CategoryController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.category.Category;
import model.category.FinalCategory;
import newViewNedaei.Panel;

import java.util.ArrayList;

public class EditCategoryPanel extends Panel {

    public Label oldNameLabel;
    public ValidatorField newNameField;
    public Button changeNameButton;
    public Pane categoryFeaturesPane;
    public ListView<String> featuresList;
    public Button addFeatureButton;
    public Button removeFeatureButton;
    public TextField newFeatureField;
    public Label errorLabel;
    private Category editingCategory;

    public static String getFxmlFilePath() {
        return "/EditCategoryPanel.fxml";
    }

    @FXML
    public void initialize() {
        editingCategory = CategoryController.getInstance().getItemById(CategoriesManagingMenu.getSelectedCategoryId());
        setUpFeaturesList();
        oldNameLabel.setText(editingCategory.getName());
    }

    private void setUpFeaturesList() {
        featuresList.setItems(FXCollections.observableArrayList());
        if (!editingCategory.isFinal()) {
            categoryFeaturesPane.setVisible(false);
            return;
        }
        ArrayList<String> categorySpecialFeatures = ((FinalCategory) editingCategory).getSpecialFeatures();
        for (String categorySpecialFeature : categorySpecialFeatures) {
            featuresList.getItems().add(categorySpecialFeature);
        }
    }

    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    public void editName() {
        if (!newNameField.validate()) {
            errorLabel.setText("invalid new name");
            return;
        }
        String newName = newNameField.getText();
        if (CategoryController.getInstance().categoryNameExists(newName)) {
            errorLabel.setText("new name already exists");
            return;
        }
        CategoryController.getInstance().editCategoryName(editingCategory, newName);
        errorLabel.setText("category name changed");
        oldNameLabel.setText(editingCategory.getName());
        newNameField.clear();
    }

    public void addFeature() {
        String newFeature = newFeatureField.getText();
        if (CategoryController.getInstance().categoryHasFeature((FinalCategory) editingCategory, newFeature)) {
            errorLabel.setText("feature already exists");
            return;
        }
        CategoryController.getInstance().addFeatureToCategory((FinalCategory) editingCategory, newFeatureField.getText());
        setUpFeaturesList();
        newFeatureField.clear();
    }

    public void removeSelectedFeature() {
        CategoryController.getInstance().removeFeatureFromCategory((FinalCategory) editingCategory, featuresList.getSelectionModel().getSelectedItem());
        setUpFeaturesList();
    }
}
