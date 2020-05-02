package view.bagheri;

import controller.ProductController;

import java.util.HashMap;
import java.util.Map;

public class ProductMenu extends Menu {
    private static ProductMenu instance = new ProductMenu();
    private ProductController productController;


    private ProductMenu() {
        super("product page", null);
//        productController = ;
        this.submenus.put("digest", createDigestPanel());
        this.submenus.put("attributes", createShowProductAttributesPanel());
        this.submenus.put("compare (\\w+)", createCompareProductsPanel());
        this.submenus.put("comments", CommentingPanel.getInstance());
    }

    public static ProductMenu getInstance() {
        return instance;
    }

    private Panel createDigestPanel() {
        return new Panel("digestPanel") {
            @Override
            public void execute() {
                //needs to be completed
            }
        };
    }

    private Panel createShowProductAttributesPanel() {
        return new Panel("showProductAttributesPanel") {
            @Override
            public void execute() {
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
            public void execute() {
                productController.getProductAttributes();
                productController.getProductAttributesById(matcher.group(1));



            }
        };
    }

    protected void showHelp() {

    }
}
