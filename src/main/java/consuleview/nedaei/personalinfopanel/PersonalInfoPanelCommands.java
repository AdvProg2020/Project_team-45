package consuleview.nedaei.personalinfopanel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum PersonalInfoPanelCommands {
    EDIT("edit\\s+(\\w+)");

    private final Pattern commandPattern;

    PersonalInfoPanelCommands(String commandPatternString) {
        this.commandPattern = Pattern.compile(commandPatternString);
    }

    public Matcher getMatcher(String input) {
        return this.commandPattern.matcher(input);
    }

}
