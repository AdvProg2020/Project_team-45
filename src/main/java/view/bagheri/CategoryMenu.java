package view.bagheri;


public class CategoryMenu extends ProductSearchMenu {
    private static CategoryMenu instance = new CategoryMenu();


    private CategoryMenu() {
        super("categoryMenu", null);
    }

    public static CategoryMenu getInstance() {
        return instance;
    }

    @Override
    protected Panel creatViewCategoriesPanel() {
        return new Panel("viewCategoriesPanel") {
            @Override
            public void execute() {
                //needs to be completed
            }
        };
    }
}