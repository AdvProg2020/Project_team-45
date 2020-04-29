package view.bagheri;

import model.ProductFilters;

public abstract class ProductSearchMenu extends Menu {
    private ProductFilters activeFilters;
    private String activeSort;


    protected ProductSearchMenu(String name, Menu parent) {
        super(name, null);
        //        productController = ;
        this.submenus.put("filtering", );
        this.submenus.put("sorting", createShowProductAttributesPanel());
    }

    protected void showAvailableFilters() {
    }

    protected void showCurrentFilters() {
    }

    protected void showAvailableSorts() {
    }

    protected void showCurrentSort(){
    }
}
