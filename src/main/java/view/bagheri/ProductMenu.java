package view.bagheri;

import controller.ProductController;

import java.util.HashMap;
import java.util.Map;

public class ProductMenu extends Menu {
    private static final ProductMenu instance = new ProductMenu();
    private final ProductController productController;


    private ProductMenu() {
        super("product page");
        productController = ProductController.getInstance();
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
                show();
                String inputCommand;
                while (!(inputCommand = scanner.nextLine()).equals("back")) {
                    if (inputCommand.equals("add to cart")) {
                        productController.addActiveProductToCart();
                    } else if ((matcher = getMatcher("select seller (\\w+)", inputCommand)) != null) {
                        selectSeller(matcher.group(1));
                    } else {
                        System.out.println("invalid command!");
                    }
                }
            }

            @Override
            protected void show() {
                HashMap<String, String> productDigestInformation = productController.getProductDigestInformation();
                for (Map.Entry<String, String> information : productDigestInformation.entrySet()) {
                    if (information.getValue() != null)
                        System.out.println(information.getKey() + ": " + information.getValue());
                }
            }

            private void selectSeller(String sellerUsername) {
                if (!productController.selectSellerForActiveProduct(sellerUsername)) {
                    System.out.println("This seller does not exist!");
                }
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
                if (productController.isWithInACategory(matcher.group(1))) {
                    productController.getProductAttributes();
                    productController.getProductAttributesById(matcher.group(1));
                    // TODO : bagheri
                } else {
                    System.out.println("It is not possible to compare these two products!");
                }
            }
        };
    }

    @Override
    protected void showHelp() {
        super.showHelp();
        System.out.println("digest\n" +
                "attributes\n" +
                "compare [productId]\n" +
                "comments");
    }
}