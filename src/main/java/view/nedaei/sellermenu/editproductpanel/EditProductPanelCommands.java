package view.nedaei.sellermenu.editproductpanel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EditProductPanelCommands {
    FIELD_AND_VALUE("(?i)(\\w+)\\s*:\\s*(\\w+)");

    private Pattern commandPattern;

    EditProductPanelCommands(String commandPatternString) {
        this.commandPattern = Pattern.compile(commandPatternString);
    }

    public Matcher getMatcher(String input) {
        return this.commandPattern.matcher(input);
    }

}
