package view.hatami;


import controller.managers.*;
import view.bagheri.Menu;
import view.bagheri.Panel;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ManagingMenu extends Menu {

    protected Manager manager;
    protected String managingObject;

    protected ManagingMenu(String name) {
        super(name);
    }

    protected static void displayAllItemsInPanel(String panelName, Printer printer) {
        new Panel(panelName) {
            private Printer printer;

            @Override
            public void execute() {
                String[] list = printer.getAllInListAsString().split("\n");
                StringBuilder output = new StringBuilder();
                for (String row : list) {
                    for (String info : row.split(",")) {
                        output.append(String.format("%-15s", info));
                    }
                    output.append("\n");
                }
                System.out.println(output);
            }

            public Panel setPrinter(Printer printer) {
                this.printer = printer;
                return this;
            }
        }.setPrinter(printer).execute();
    }

    protected void show() {
        super.show();
        displayAllItemsInPanel("ALL " + managingObject + ":", (Printer) manager);
    }

    protected static Panel createOneItemDisplayPanel(String panelName, Printer printer) {
        return new Panel(panelName) {
            private Printer printer;

            @Override
            public void execute() {
                String[] info = printer.printDetailedById(matcher.group(1)).split(",");
                StringBuilder output = new StringBuilder();
                for (int count = 0; count < info.length; count++) {
                    output.append(String.format("%-20s", info[count]));
                    if (count % 3 == 2)
                        output.append("\n");
                }
                System.out.println(output);
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
