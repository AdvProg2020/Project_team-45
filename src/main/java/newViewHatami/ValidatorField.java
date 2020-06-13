package newViewHatami;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ValidatorField extends TextField {

    private String validatorRegex;

    private final StringProperty validation = new SimpleStringProperty(this, "fieldType", ".*");
//    public final StringProperty fieldTypeProperty() { return fieldType; }
    public final String getValidation() { return validation.get(); }
    public final void setValidation(String value) { validation.set(value); }

    private final StringProperty isNecessary = new SimpleStringProperty(this, "fieldType", ".*");
    //    public final StringProperty fieldTypeProperty() { return fieldType; }
    public final String getIsNecessary() { return isNecessary.get(); }
    public final void setIsNecessary(String value) { isNecessary.set(value); }

    @FXML
    public boolean validate() {
        if (validatorRegex == null) {
            if (!setValidatorRegex())
                return true;
        }
        String value = getText();
        if (value.matches(validatorRegex)) {
            this.setStyle("-fx-text-box-border: #039ed3;");
            return true;
        }

        this.setStyle("-fx-text-box-border: #fc0606;");
        return false;
    }

    private boolean setValidatorRegex() {
        if (getValidation() == null)
            return false;
        switch (getValidation()) {
            case "alphaNumeric":
                validatorRegex = "\\w+";
                break;
            case "englishTense":
                validatorRegex = "[a-zA-Z ]+";
                break;
            case "emailAddress":
                validatorRegex = "\\w+@\\w+.\\w+";
                break;
        }
        return true;
    }

}
