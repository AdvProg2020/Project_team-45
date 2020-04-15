package view;

import model.ProductFilters;

import java.util.ArrayList;

public class ProductSearchingMenu extends ManagingMenu {
    private ProductFilters activeFilters;
    private ArrayList<String> availableFilters;
    private model.Category activeCategory;
}
