package newViewHatami;

import controller.CategoryController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import model.Market;
import model.category.Category;
import model.category.ParentCategory;
import newViewNedaei.Panel;

import java.util.HashMap;
import java.util.List;

public class CreateCategoryPanel extends Panel {

    public Label errorLabel;
    public TextArea categoryFeaturesInput;

    public static String getFxmlFilePath() {
        return "/CreateCategoryPanel.fxml";
    }

    @FXML
    public void initialize() {
        setUpChoiceBox();

    }

    private void setUpChoiceBox() {
        parentCategoryChoiceBox.setItems(FXCollections.observableArrayList());
        ParentCategory nullCategory = Category.getNullCategory();
        parentCategoryChoiceBox.getItems().add(nullCategory);

        List<ParentCategory> parentCategories = CategoryController.getInstance().getParentCategories();
        for (ParentCategory parentCategory : parentCategories) {
            parentCategoryChoiceBox.getItems().add(parentCategory);
        }
        parentCategoryChoiceBox.setValue(null);
        parentCategoryChoiceBox.setValue(nullCategory);
    }

    public ChoiceBox<ParentCategory> parentCategoryChoiceBox;
    public CheckBox finalCategoryCheckBox;
    public ValidatorField categoryName;


    public void createCategory() {
        if (!categoryName.validate()) {
            errorLabel.setText("invalid category name");
            return;
        }
        if (Market.getInstance().getCategoryByName(categoryName.getText()) != null) {
            errorLabel.setText("category name already exists");
            return;
        }

        HashMap<String, String> categoryInfo = new HashMap<>();
        categoryInfo.put("name", categoryName.getText());
        categoryInfo.put("is final?", finalCategoryCheckBox.isSelected() ? "yes" : "no");
        categoryInfo.put("features", categoryFeaturesInput.getText());
        categoryInfo.put("parent category", parentCategoryChoiceBox.getValue().getName());

        CategoryController.getInstance().createItem(categoryInfo);
        errorLabel.setText("category made successfully");
        categoryName.clear();
        setUpChoiceBox();
    }


    public void validate(KeyEvent keyEvent) {
        ((Validator) keyEvent.getSource()).validate();
    }

    public void setSpecialFeaturesFields() {
        categoryFeaturesInput.setDisable(!finalCategoryCheckBox.isSelected());
    }
}
