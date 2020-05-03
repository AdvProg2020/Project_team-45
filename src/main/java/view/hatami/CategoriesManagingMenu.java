package view.hatami;

import model.Category;
import view.bagheri.Menu;
import view.bagheri.Panel;
import view.nedaei.personalinfopanel.PersonalInfoPanel;

public class CategoriesManagingMenu extends ManagingMenu {
    public CategoriesManagingMenu(Menu parent) {
        super("categories managing menu", parent);
    }

    private PersonalInfoPanel getCategoryEditorPanel(Category category) {
    }

    private Panel getCategoryCreatorPanel() {
    }
}
