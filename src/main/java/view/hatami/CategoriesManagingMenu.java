package view.hatami;

import controller.CategoryController;
import controller.managers.Creator;
import controller.managers.Deleter;
import controller.managers.Editor;

public class CategoriesManagingMenu extends ManagingMenu {
    public CategoriesManagingMenu() {
        super("categories managing menu");
        this.manager = CategoryController.getInstance();
        submenus.put("edit (\\S+)", createItemEditorPanel("edit category", (Editor) manager));
        submenus.put("add (\\S+)", createItemCreatorPanel("add category", (Creator) manager));
        submenus.put("remove (\\S+)", createItemDeleterPanel("delete category", (Deleter) manager));
    }

}
