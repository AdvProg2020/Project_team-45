package controller;

import java.util.regex.Pattern;

public class InputValidator {
    private Pattern validPattern;
    private String formatToShow;

    public InputValidator(String validRegex, String formatToShow) {
        this.validPattern = Pattern.compile(validRegex);
        this.formatToShow = formatToShow;
    }

    public String getFormatToShow() {
        return formatToShow;
    }

    public boolean checkInput(String input) {
        if (input == null)
            return false;
        return validPattern.matcher(input).matches();
    }

    public static InputValidator getSimpleTextValidator(){
        return new InputValidator("\\w+", "alphanumeric, non space characters");
    }
    public static InputValidator getEmailAddressValidator(){
        return new InputValidator("\\w+@\\w+.\\w+", "Email Address");
    }
    public static InputValidator getSimpleNumberValidator(){
        return new InputValidator("\\d{5,12}", "5-12 numbers");
    }

}
