package view.sellermenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EditPanelsCommands {
    FIELD_AND_VALUE("(\\w+)\\s*:\\s*(\\w+)");

    private final Pattern commandPattern;

    EditPanelsCommands(String commandPatternString) {
        this.commandPattern = Pattern.compile(commandPatternString);
    }

    public Matcher getMatcher(String input) {
        return this.commandPattern.matcher(input);
    }

}
