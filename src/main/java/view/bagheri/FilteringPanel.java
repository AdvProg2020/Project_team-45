package view.bagheri;

public class FilteringPanel extends Panel {
    private static FilteringPanel filteringPanel = new FilteringPanel();


    private FilteringPanel() {
        super("filteringPanel");
    }

    public static FilteringPanel getInstance() {
        return filteringPanel;
    }

    @Override
    public void execute() {
        String inputCommand;
        while (!(inputCommand = scanner.nextLine()).equals("back")) {
            if (inputCommand.equals("show available filters")) {

            } else if(inputCommand.matches("sort (\\.+)")) {

            } else if(inputCommand.equals("current sort")) {

            } else if(inputCommand.equals("disable filter (\\.+)")) {

            } else {
                System.out.println("invalid command!");
            }
        }
    }
}
