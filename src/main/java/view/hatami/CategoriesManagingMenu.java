package view.hatami;

import controller.CategoryController;
import controller.InputValidator;
import controller.managers.Creator;
import controller.managers.Deleter;
import controller.managers.Editor;
import controller.userControllers.UserController;
import view.bagheri.Panel;

import java.util.ArrayList;

public class CategoriesManagingMenu extends ManagingMenu {
    public CategoriesManagingMenu() {
        super("categories managing menu");
        this.manager = CategoryController.getInstance();
        this.managingObject = "Categories";
        submenus.put("edit (\\S+)", createItemEditorPanel("edit category", (Editor) manager));
        submenus.put("add (\\S+)", createItemCreatorPanel("add category", (Creator) manager));
        submenus.put("remove (\\S+)", createItemDeleterPanel("delete category", (Deleter) manager));
    }

    public static ArrayList<String> getCategoryFeatures(){
        return new Panel("get category features"){

            private final InputValidator validator = InputValidator.getCategoryFeaturesValidator();
            private  ArrayList<String> categoryFeatures;
            @Override
            public void execute() {
                categoryFeatures = new ArrayList<>();
                System.out.println(validator.getFormatToShow());
                String feature;
                while (!(feature = scanner.nextLine()).equals("done")){
                    if (validator.checkInput(feature))
                        categoryFeatures.add(feature);
                    else
                        System.out.println("invalid input");
                }
            }

            public ArrayList<String> getCategoryFeatures() {
                execute();
                return categoryFeatures;
            }
        }.getCategoryFeatures();
    }

    @Override
    public void execute() {
        if (!UserController.isBuyerLoggedIn()) {
            back();
            return;
        }
        super.execute();
    }
}
