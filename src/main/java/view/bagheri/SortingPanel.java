package view.bagheri;

import controller.SortingController;

import java.util.ArrayList;

public class SortingPanel extends Panel {
    private static final SortingPanel instance = new SortingPanel();
    private final SortingController sortingController;


    private SortingPanel() {
        super("sortingPanel");
        sortingController = SortingController.getInstance();
    }

    public static SortingPanel getInstance() {
        return instance;
    }

    @Override
    public void execute() {
        String inputCommand;
        while (!(inputCommand = scanner.nextLine()).equals("back")) {
            if (inputCommand.equals("show available sorts")) {
                showAvailableSorts();
            } else if((matcher = getMatcher("sort (.+)", inputCommand)) != null) {
                sorting(matcher.group(1));
            } else if(inputCommand.equals("current sort")) {
                System.out.println(sortingController.showCurrentSort());
            } else if(inputCommand.equals("disable sort")) {
                sortingController.disableCurrentSort();
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private void showAvailableSorts() {
        ArrayList<String> availableSorts = sortingController.getAvailableSorts();
        if (availableSorts.isEmpty()) {
            System.out.println("There is no sort to display!");
        }
        for (String availableSort : availableSorts) {
            System.out.println(availableSort);
        }
    }

    private void sorting(String sort) {
        if (!sortingController.setActiveSort(sort)) {
            System.out.println("There is no sort with this name!");
        }
    }
}