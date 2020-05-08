package view.bagheri;

public class SortingPanel extends Panel {
    private static final SortingPanel instance = new SortingPanel();


    private SortingPanel() {
        super("sortingPanel");
    }

    public static SortingPanel getInstance() {
        return instance;
    }

    @Override
    public void execute() {
        String inputCommand;
        while (!(inputCommand = scanner.nextLine()).equals("back")) {
            if (inputCommand.equals("show available sorts")) {

            } else if((matcher = getMatcher("sort (\\.+)", inputCommand)) != null) {

            } else if(inputCommand.equals("current sort")) {

            } else if(inputCommand.equals("disable sort")) {

            } else {
                System.out.println("invalid command!");
            }
        }
    }
}
