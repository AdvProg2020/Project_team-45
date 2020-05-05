package view.hatami;

import controller.CategoryController;
import controller.Deleter;
import controller.Editor;
import view.bagheri.Panel;

public class CategoriesManagingMenu extends ManagingMenu {
    public CategoriesManagingMenu() {
        super("categories managing menu");
        this.printer = CategoryController.getInstance();
        submenus.put("edit (\\S+)", createItemEditorPanel("edit category", (Editor) printer));
        submenus.put("add (\\S+)", getCategoryCreatorPanel());
        submenus.put("remove (\\S+)", createItemDeleterPanel("delete category", (Deleter) printer));
    }

    private Panel getCategoryCreatorPanel(){
        return new Panel("create category") {
            @Override
            public void execute() {
                // TODO : hatami (can this go into manging menu too?)
            }
        };
    }

}
