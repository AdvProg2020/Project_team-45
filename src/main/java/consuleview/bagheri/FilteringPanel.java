package consuleview.bagheri;

import controller.FilteringController;
import consuleview.Panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            } else if((matcher = getMatcher("filter (.+): (.+)", inputCommand)) != null) {
                addFilter(matcher.group(1), matcher.group(2));
            } else if(inputCommand.equals("current filters")) {
               showCurrentFilters();
            } else if((matcher = getMatcher("disable filter (.+): (.+)", inputCommand)) != null) {
                removeFilter(matcher.group(1), matcher.group(2));
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

    private void addFilter(String type, String value) {
        if (!filteringController.addFilter(type, value)) {
            System.out.println("There is no filter with this name!");
        }
    }

    private void showCurrentFilters() {
        HashMap<String, String> currentFilters = filteringController.getCurrentFilters();
        if (currentFilters.isEmpty()) {
            System.out.println("No filter selected.");
        }
        for (Map.Entry<String, String> currentFilter : currentFilters.entrySet()) {
            System.out.println(currentFilter.getKey() + ": " + currentFilter.getValue());
        }
    }

    private void removeFilter(String type, String value) {
        filteringController.removeFilter(type, value);
    }

    private void removeFilter(String filter) {
        if (!filteringController.removeFilter(filter)) {
            System.out.println("There is no filter with this name!");
        }
    }
}
