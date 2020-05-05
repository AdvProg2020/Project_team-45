package view.hatami;


import controller.Manager;
import controller.Printer;
import view.bagheri.Menu;
import view.bagheri.Panel;

public abstract class ManagingMenu extends Menu {

    protected Printer printer;

    public ManagingMenu(String name, Menu parent) {
        super(name, parent);
    }

    protected static Panel createAllItemsDisplayPanel(String panelName, final Printer printer) {
        return new Panel(panelName) {
            @Override
            protected void execute() {
                System.out.println(printer.printAllInList());
            }
        };
    }

    protected static Panel createOneItemDisplayPanel(String panelName, final Printer printer) {
        return new Panel(panelName) {
            @Override
            protected void execute() {
                System.out.println(printer.printDetailedById(matcher.group(1)));
            }
        };
    }

    protected static Panel createItemDeletePanel(String panelName, final Manager manager){
        return new Panel(panelName) {
            @Override
            protected void execute() {

            }
        };
    }
}
