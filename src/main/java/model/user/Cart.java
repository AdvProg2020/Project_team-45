package model.user;

import model.product.Product;
import model.product.ProductSellInfo;

import java.util.ArrayList;
import java.util.HashMap;


public class Cart {
    private final ArrayList<ProductInfo> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public HashMap<String, Integer> getProductsDisplay() {
        HashMap<String, Integer> productsAndAmounts = new HashMap<>();
        for (ProductInfo productInfo : products) {
            productsAndAmounts.put("productName = " + productInfo.getProduct().getName() + ", productId = " +
                            productInfo.getProduct().getId() + ", amount = ", productInfo.getAmount());
        }
        return productsAndAmounts;
    }

    private ProductInfo getProductById(String productId) {
        for (ProductInfo productInfo : products) {
            if (productInfo.getProduct().getId().equals(productId)) {
                return productInfo;
            }
        }
        return null;
    }

    public int getProductAmountById(String productId) {
        for (ProductInfo productInfo : products) {
            if (productInfo.getProductSellInfo().getId().equals(productId)) {
                return productInfo.getAmount();
            }
        }
        //System.out.println(products.size());
        return 0;
    }

    public void changeProductAmountById(String productId, int amountChange) {
        ProductInfo productInfo = getProductById(productId);
        if (productInfo == null) {
            return;
        }
        productInfo.changeAmount(amountChange);
    }

    public int getTotalPrice() {
        int result = 0;
        for (ProductInfo productInfo : products) {
            //System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$" + productInfo.getProductSellInfo().getOff().getOffId());
            result += productInfo.getProductSellInfo().getFinalPrice() * productInfo.getAmount();
        }
        return result;
    }

    public ArrayList<ProductSellInfo> getProductSellInfos() {
        ArrayList<ProductSellInfo> result = new ArrayList<>();
        for (ProductInfo productInfo : products) {
            result.add(productInfo.getProductSellInfo());
        }
        return result;
    }

    public ArrayList<Product> getCartProducts() {
        ArrayList<Product> result = new ArrayList<>();
        for (ProductInfo productInfo : products) {
            result.add(productInfo.getProduct());
        }
        return result;
    }

    public void addProduct(Product product, ProductSellInfo productSellInfo) {
        products.add(new ProductInfo(product, productSellInfo));
        //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^" + products.size());
    }

    public void removeProduct(ProductSellInfo productSellInfo) {
        products.removeIf(productInfo -> productInfo.getProductSellInfo().getId().equals(productSellInfo.getId()));
    }

    public void changeProductByProductIdAndSellerUsername(String productId, String sellerUsername, int amountChange) {

    }

    public ArrayList<ProductSellInfo> getSellInfos() {
        ArrayList<ProductSellInfo> result = new ArrayList<>();
        for (ProductInfo productInfo : products) {
            result.add(productInfo.getProductSellInfo());
        }
        return result;
    }

    class ProductInfo {
        private final Product product;
        private final ProductSellInfo productSellInfo;
        private int amount;

        public ProductInfo(Product product, ProductSellInfo productSellInfo) {
            this.product = product;
            this.productSellInfo = productSellInfo;
            this.amount = 1;
        }

        public Product getProduct() {
            return product;
        }

        public ProductSellInfo getProductSellInfo() {
            return productSellInfo;
        }

        public int getAmount() {
            return amount;
        }

        public void changeAmount(int amountChange) {
            amount += amountChange;
            if (amount < 0) {
                amount = 0;
            }
        }
    }

}



