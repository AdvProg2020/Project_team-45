package view;

import controller.ProductController;
import model.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductMenu extends Menu {
    private static ProductMenu instance = new ProductMenu();
    private ProductController productController;


    private ProductMenu() {
        super("product page", null);
//        productController = ;
        this.submenus.put("digest", );
        this.submenus.put("attributes", createShowProductAttributesPanel());
        this.submenus.put("compare (\\w+)", createCompareProductsPanel());
        this.submenus.put("comments", CommentingPanel.getInstance());
    }

    public static ProductMenu getInstance() {
        return instance;
    }

    protected void showHelp() {

    }

    private Panel createShowProductAttributesPanel() {
        return new Panel("showProductAttributesPanel") {
            @Override
            protected void execute() {
                HashMap<String, String> productAttributes = productController.getProductAttributes();
                for (Map.Entry<String, String> attribute : productAttributes.entrySet()) {
                    if (attribute.getValue() != null)
                        System.out.println(attribute.getKey() + ": " + attribute.getValue());
                }
            }
        };
    }

    private Panel createCompareProductsPanel() {
        return new Panel("compareProductsPanel") {
            @Override
            protected void execute() {
                productController.getProductAttributes();
                productController.getProductAttributesById(matcher.group(1));



            }
        };
    }
}
