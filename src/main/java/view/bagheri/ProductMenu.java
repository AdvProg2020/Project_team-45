package view.bagheri;

import controller.ProductController;

import java.util.LinkedHashMap;
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
                LinkedHashMap<String, String> productDigestInformation = productController.getProductDigestInformation();
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
                LinkedHashMap<String, String> productAttributes = productController.getProductAttributes();
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
                LinkedHashMap<String, String> secondProductAttributes = productController.getProductAttributesById(matcher.group(1));
                if (secondProductAttributes == null) {
                    System.out.println("It is not possible to compare these two products!");
                } else {
                    LinkedHashMap<String, String> firstProductAttributes = productController.getProductAttributes();
                    // TODO : bagheri
                }
            }
        };
    }

    @Override
    protected boolean check() {
        if (matcher.group().startsWith("view") && productController.setActiveProductBYProductIdForCart(matcher.group(1))) {
            return true;
        } else if (productController.setActiveProductBYProductIdForCategory(matcher.group(1))) {
            return true;
        }
        System.out.println("There is no product with this Id in this category!");
        return false;
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