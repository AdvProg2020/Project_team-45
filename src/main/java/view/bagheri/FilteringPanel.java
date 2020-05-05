package view.bagheri;

public class FilteringPanel extends Panel {
    private static FilteringPanel instance = new FilteringPanel();


    private FilteringPanel() {
        super("filteringPanel");
    }

    public static FilteringPanel getInstance() {
        return instance;
    }

    @Override
    protected void execute() {
        String inputCommand;
        while (!(inputCommand = scanner.nextLine()).equals("back")) {
            if (inputCommand.equals("show available filters")) {

            } else if((matcher = getMatcher("filter (\\.+)", inputCommand)) != null) {

            } else if(inputCommand.equals("current sort")) {

            } else if((matcher = getMatcher("disable filter (\\.+)", inputCommand)) != null) {

            } else {
                System.out.println("invalid command!");
            }
        }
    }
}
