package controller;

import controller.managers.Manager;
import controller.userControllers.BuyerController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class InputValidator {
    private Pattern validPattern;
    private String formatToShow;
    private Manager existenceChecker;

    public InputValidator(String validRegex, String formatToShow) {
        this.validPattern = Pattern.compile(validRegex);
        this.formatToShow = formatToShow;
    }

    public InputValidator(String validRegex, String formatToShow, Manager existenceChecker) {
        this(validRegex, formatToShow);
        this.existenceChecker = existenceChecker;
    }

    public String getFormatToShow() {
        return formatToShow;
    }

    public boolean checkInput(String input) {
        if (input == null)
            return false;
        if (existenceChecker != null && existenceChecker.getItemById(input) == null)
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
    public static InputValidator getDateValidator(){
        return new InputValidator("([0][1-9]|[1-2][0-9]|[3][01])/([0][1-9]|[1][012])/19[5-9][0-9]|20\\d\\d", "DD/MM/YYYY");
    }
    public static InputValidator getPercentageValidator(){
        return new InputValidator("[0][1-9]|[1-9][0-9]", "1-99 digit");
    }
    public static InputValidator getExistingBuyerValidator(){
        return new InputValidator("\\w+", "existing username", BuyerController.getInstance());
    }
    public static InputValidator getCategoryFeaturesValidator(){
        return new InputValidator("([\\w| ]+-)+", "separate features names with '-'");
    }

    public static Date convertStringToDate(String dateString){
        Date date= null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException ignored) {}
        return date;
    }
}
