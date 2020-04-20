package controller;

import model.Market;
import model.ProductFilters;

import java.util.HashMap;

public class MainController {
    private Market market;
    private UserController userController;
    private CartController cartController;
    private CodedDiscountController codedDiscountController;
    private CategoryController categoryController;
    private ProductController productController;
    private ProductFilteringController productFilteringController;
    private ProductSortingController productSortingController;
    private OffController offController;
    private OffFilteringController offFilteringController;
    private OffSortingController offSortingController;
    private RequestController requestController;

    public MainController() {
        this.market = Market.getInstance();
        this.userController = new UserController(this);
        this.cartController = new CartController(this);
        this.codedDiscountController = new CodedDiscountController(this);
        this.categoryController = new CategoryController(this);
        this.productController = new ProductController(this);
        this.productFilteringController = new ProductFilteringController(this);
        this.productSortingController = new ProductSortingController(this);
        this.offController =  new OffController(this);
        this.offFilteringController = new OffFilteringController(this);
        this.offSortingController = new OffSortingController(this);
        this.requestController = new RequestController(this);
    }

    //================================================================================
    // methods to get a HashMap<String, Object> containing all Objects of a certain type
    //================================================================================

    public HashMap<String, Object> getAllUsersList() {
        return null;
    }

    public HashMap<String, Object> getAllCategoriesList() {
        return null;
    }

    public HashMap<String, Object> getAllProductsList(ProductFilters productFilters) {
        return null;
    }

    public HashMap<String, Object> getAllDiscountCodesList() {
        return null;
    }

    public HashMap<String, Object> getAllOffsList() {
        return null;
    }

    public HashMap<String, Object> getAllRequestsList() {
        return null;
    }

}
