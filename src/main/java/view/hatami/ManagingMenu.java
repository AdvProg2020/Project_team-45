package view.hatami;


import controller.Deleter;
import controller.Printer;
import view.bagheri.Menu;
import view.bagheri.Panel;

public abstract class ManagingMenu extends Menu {

    protected Printer printer;

    protected ManagingMenu(String name) {
        super(name);
    }

    protected static void displayAllItemsInPanel(String panelName, Printer printer) {
        new Panel(panelName) {
            private Printer printer;

            @Override
            public void execute() {
                System.out.println(printer.printAllInList());
            }

            public Panel setPrinter(Printer printer) {
                this.printer = printer;
                return this;
            }
        }.setPrinter(printer).execute();
    }

    protected void show() {
        super.show();
        displayAllItemsInPanel("all Users:", printer);
    }

    protected static Panel createOneItemDisplayPanel(String panelName, Printer printer) {
        return new Panel(panelName) {
            private Printer printer;

            @Override
            public void execute() {
                System.out.println(printer.printDetailedById(matcher.group(1)));
            }

            public Panel setPrinter(Printer printer) {
                this.printer = printer;
                return this;
            }
        }.setPrinter(printer);
    }

    protected static Panel createItemDeleterPanel(String panelName, Deleter deleter) {
        return new Panel(panelName) {
            private Deleter deleter;

            @Override
            public void execute() {
                this.deleter.deleteItemById(matcher.group(1));
            }

            public Panel setManager(Deleter deleter) {
                this.deleter = deleter;
                return this;
            }
        }.setManager(deleter);
    }

    protected static Panel createItemEditorPanel(String panelName, Deleter deleter) {

    }
}
