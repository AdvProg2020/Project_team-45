package view.bagheri;


public abstract class ProductSearchMenu extends Menu {


    protected ProductSearchMenu(String name) {
        super(name);
        submenus.put("view categories", creatViewCategoriesPanel());
        submenus.put("filtering", FilteringPanel.getInstance());
        submenus.put("sorting", SortingPanel.getInstance());
        submenus.put("show products", creatShowProductsPanel());
        submenus.put("show product (\\w+)", ProductMenu.getInstance());
    }

    protected abstract Panel creatViewCategoriesPanel();

    protected Panel creatShowProductsPanel() {
        return new Panel("showProductPanel") {
            @Override
            public void execute() {
                showProduct();
            }
        };
    }

    protected void showProduct() {
        //needs to be completed
    }

    @Override
    protected void showHelp() {
        super.showHelp();
        //needs to be completed
    }
}