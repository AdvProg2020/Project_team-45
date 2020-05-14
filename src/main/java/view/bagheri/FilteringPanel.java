package view.bagheri;

import controller.FilteringController;

import java.util.ArrayList;

public class FilteringPanel extends Panel {
    private static final FilteringPanel instance = new FilteringPanel();
    private final FilteringController filteringController;


    private FilteringPanel() {
        super("filteringPanel");
        filteringController = FilteringController.getInstance();
    }

    public static FilteringPanel getInstance() {
        return instance;
    }

    @Override
    public void execute() {
        String inputCommand;
        while (!(inputCommand = scanner.nextLine()).equals("back")) {
            if (inputCommand.equals("show available filters")) {
                showAvailableFilters();
            } else if((matcher = getMatcher("filter (.+)", inputCommand)) != null) {
                addFilter(matcher.group(1));
            } else if(inputCommand.equals("current filters")) {
               showCurrentFilters();
            } else if((matcher = getMatcher("disable filter (.+)", inputCommand)) != null) {
                removeFilter(matcher.group(1));
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private void showAvailableFilters() {
        ArrayList<String> availableFilters = filteringController.getAvailableFilters();
        if (availableFilters.isEmpty()) {
            System.out.println("There is no filter to display!");
        }
        for (String availableFilter : availableFilters) {
            System.out.println(availableFilter);
        }
    }

    private void addFilter(String filter) {
        if (!filteringController.addFilter(filter)) {
            System.out.println("There is no filter with this name!");
        }
    }

    private void showCurrentFilters() {
        ArrayList<String> currentFilters = filteringController.getCurrentFilters();
        if (currentFilters.isEmpty()) {
            System.out.println("No filter selected.");
        }
        for (String currentFilter : currentFilters) {
            System.out.println(currentFilter);
        }
    }

    private void removeFilter(String filter) {
        if (!filteringController.removeFilter(filter)) {
            System.out.println("There is no filter with this name!");
        }
    }
}
