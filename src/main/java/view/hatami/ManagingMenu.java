package view.hatami;


import controller.InputValidator;
import controller.managers.*;
import view.bagheri.Menu;
import view.bagheri.Panel;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ManagingMenu extends Menu {

    protected Manager manager;
    protected String managingObject;

    protected ManagingMenu(String name) {
        super(name);
    }

    public ManagingMenu() {

    }

    protected static void displayAllItemsInPanel(String panelName, Printer printer) {
        new Panel(panelName) {
            private Printer printer;

            @Override
            public void execute() {
                System.out.println(name);
                String[] list = printer.getAllInListAsString().split("\n");
                StringBuilder output = new StringBuilder();
                for (String row : list) {
                    for (String info : row.split(",")) {
                        output.append(String.format("%-20s", info));
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
                try {
                    if (printer.getDetailStringById(matcher.group(1)) == null){
                        System.out.println("wrong Id");
                        return;
                    }
                    String[] info = printer.getDetailStringById(matcher.group(1)).split(",");
                    StringBuilder output = new StringBuilder();
                    for (int count = 0; count < info.length; count++) {
                        output.append(String.format("%-20s", info[count]));
                        if (count % 3 == 2)
                            output.append("\n");
                    }
                    System.out.println(output);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
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
                try {
                    if (this.deleter.deleteItemById(matcher.group(1)))
                        System.out.println(matcher.group(1) + " deleted successfully!");
                    else
                        System.out.println("wrong Id");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
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
            private Object editingObject;
            private HashMap<String, InputValidator> availableFields;
            private Pattern validEditPattern = Pattern.compile("^edit (//S+) to (//S+)$");

            @Override
            public void execute() {
                if (editingObject == null){
                    System.out.println("wrong id");
                    return;
                }
                HashMap<String, String> changedFields = new HashMap<>();
                InputValidator validator;
                String input;
                show();
                while (true) {
                    input = scanner.nextLine();
                    Matcher inputMatcher = validEditPattern.matcher(input);
                    input = input.trim().toLowerCase();
                    if (!inputMatcher.matches()) {
                        if (input.equals("help"))
                            showHelp();
                        else if (input.equals("done"))
                            break;
                        else
                            System.out.println("invalid command");
                    } else {
                        if (!availableFields.containsKey(inputMatcher.group(1)))
                            System.out.println("invalid field name");
                        else {
                            validator = availableFields.get(matcher.group(1));
                            if (validator.checkInput(matcher.group(2)))
                                changedFields.put(matcher.group(1), matcher.group(2));
                            else
                                System.out.println("invalid format");
                        }
                    }
                }
                editor.editItem(editingObject, changedFields);
            }

            @Override
            protected void show() {
                super.show();
                System.out.println("write 'help' to see available fields to edit\n" +
                        "write 'done' to exit editing panel\n" +
                        "to edit a field write 'edit [fields name] to [new value]'");
            }

            private void showHelp() {
                System.out.println("available fields:");
                for (String fieldName : availableFields.keySet()) {
                    System.out.println(fieldName + "(" + availableFields.get(fieldName).getFormatToShow() + ")");
                }
            }

            public Panel setUp(Editor editor) {
                this.editor = editor;
                availableFields = editor.getAvailableFieldsToEdit();
                editingObject = editor.getItemById(matcher.group(1));
                return this;
            }
        }.setUp(editor);
    }

    public static Panel createItemCreatorPanel(String panelName, Creator creator) {
        return new Panel(panelName) {
            private Creator creator;
            private HashMap<String, InputValidator> necessaryFieldsToGet;
            private HashMap<String, InputValidator> optionalFieldsToGet;


            @Override
            public void execute() {
                HashMap<String, String> filledFields = new HashMap<>();
                InputValidator validator;
                String input;
                for (String fieldName : necessaryFieldsToGet.keySet()) {
                    validator = necessaryFieldsToGet.get(fieldName);
                    while (true) {
                        System.out.println(fieldName + ": (" + validator.getFormatToShow() + ")");
                        input = scanner.nextLine();
                        if (!validator.checkInput(input))
                            System.out.println("invalid format");
                        else
                            break;
                    }
                    filledFields.put(fieldName, input);
                }
                if (optionalFieldsToGet != null) {
                    for (String fieldName : optionalFieldsToGet.keySet()) {
                        validator = optionalFieldsToGet.get(fieldName);
                        while (true) {
                            System.out.println(fieldName + ": (" + validator.getFormatToShow() + ")" + "(optional, type skip to skip)");
                            input = scanner.nextLine();
                            if (input.equals("skip"))
                                break;
                            else if (!validator.checkInput(input))
                                System.out.println("invalid format");
                            else {
                                filledFields.put(fieldName, input);
                                break;
                            }
                        }
                    }
                }
                creator.createItem(filledFields);
                System.out.println("created");
            }

            public Panel setCreatorAndMap(Creator creator) {
                this.creator = creator;
                necessaryFieldsToGet = creator.getNecessaryFieldsToCreate();
                optionalFieldsToGet = creator.getOptionalFieldsToCreate();
                return this;
            }
        }.setCreatorAndMap(creator);
    }
}
