package newViewHatami;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ValidatorField extends TextField {

    private String validatorRegex;

    private final StringProperty validation = new SimpleStringProperty(this, "fieldType", "none");
//    public final StringProperty fieldTypeProperty() { return fieldType; }
    public final String getValidation() { return validation.get(); }
    public final void setValidation(String value) { validation.set(value); }

    private final BooleanProperty isNecessary = new SimpleBooleanProperty(this, "fieldType", false);
    //    public final StringProperty fieldTypeProperty() { return fieldType; }
    public final boolean getIsNecessary() { return isNecessary.get(); }
    public final void setIsNecessary(boolean value) { isNecessary.set(value); }

    @FXML
    public boolean validate() {
        if (getText().equals("")) {
            if (isNecessary.get()) {
                this.setStyle("-fx-text-box-border: #fc0606;");
                return false;
            }
            else {
                this.setStyle("-fx-text-box-border: #039ed3;");
                return true;
            }
        }
        if (validatorRegex == null) {
            setValidatorRegex();
        }
        String value = getText();
        if (value.matches(validatorRegex)) {
            this.setStyle("-fx-text-box-border: #039ed3;");
            return true;
        }

        this.setStyle("-fx-text-box-border: #fc0606;");
        return false;
    }

    private void setValidatorRegex() {
        switch (getValidation()) {
            case "none":
                validatorRegex = ".*";
                break;
            case "alphaNumeric":
                validatorRegex = "\\w*";
                break;
            case "englishTense":
                validatorRegex = "[a-zA-Z ]*";
                break;
            case "emailAddress":
                validatorRegex = "\\w+@\\w+.\\w+";
                break;
        }
    }

}
