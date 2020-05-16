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
}
