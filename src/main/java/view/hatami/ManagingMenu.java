package view.hatami;


import controller.managers.*;
import view.bagheri.Menu;
import view.bagheri.Panel;

import java.util.HashMap;

public abstract class ManagingMenu extends Menu {

    protected Manager manager;

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
        displayAllItemsInPanel("all Users:", (Printer) manager);
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

    protected static Panel createItemEditorPanel(String panelName, Editor editor) {
        return new Panel(panelName) {
            private Editor editor;
            private HashMap<String, String> availableFields;

            @Override
            public void execute() {
                // TODO : hatami
            }

            public Panel setEditorAndMap(Editor editor) {
                this.editor = editor;
                availableFields = editor.getAvailableFields();
                return this;
            }
        }.setEditorAndMap(editor);
    }

    protected static Panel createItemCreatorPanel(String panelName, Creator creator) {
        return new Panel(panelName) {
            private Creator creator;
            private HashMap<String, String> fieldsToGet;

            @Override
            public void execute() {
                // TODO : hatami
            }

            public Panel setEditorAndMap(Creator creator) {
                this.creator = creator;
                fieldsToGet = creator.getNecessaryFields();
                return this;
            }
        }.setEditorAndMap(creator);
    }
}
