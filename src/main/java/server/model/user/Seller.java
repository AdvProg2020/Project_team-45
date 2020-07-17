package server.model.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.model.Company;
import server.model.Market;
import server.model.Off;
import server.model.log.SellLog;
import server.model.product.Product;
import server.model.product.ProductSellInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends User {
    private Company company;
    private ArrayList<SellLog> listOfSellLogs;
    private HashMap<Product, ProductSellInfo> availableProducts;
    private HashMap<String, Off> listOfOffs; // offIds and offs
    private int balance;

    public Seller(PersonalInfo personalInfo, Company company) {
        super(personalInfo);
        this.company = company;
        this.listOfSellLogs = new ArrayList<>();
        listOfOffs = new HashMap<>();
        availableProducts = new HashMap<>();
        listOfOffs = new HashMap<>();
    }

    public Seller(String id) {
        super(id);
    }

    public Company getCompany() {
        return company;
    }

    public ArrayList<SellLog> getListOfSellLogs() {
        return listOfSellLogs;
    }

    public HashMap<Product, ProductSellInfo> getAvailableProducts() {
        return availableProducts;
    }

    public Product getAvailableProductById(String productId) {
        for (Product product : availableProducts.keySet()) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public ProductSellInfo getAvailableProductSellInfoById(String productId) {
        for (Product product : availableProducts.keySet()) {
            if (product.getId().equals(productId)) {
                return availableProducts.get(product);
            }
        }
        return null;
    }

    public HashMap<String, Off> getListOfOffs() {
        return listOfOffs;
    }

    public int getBalance() {
        return balance;
    }

    public Off getOffByOffId(String offId) {
        return listOfOffs.get(offId);
    }

    public void addProduct(Product product, ProductSellInfo productSellInfo) {

    }

    public void removeProductByProductId(String productId) {
        for (Product product : availableProducts.keySet()) {
            if (product.getId().equalsIgnoreCase(productId)) {
                availableProducts.remove(product);
            }
        }
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String getRole() {
        return "seller";
    }

    public void removeProductFromSellerList(Product product) {
        availableProducts.remove(product);
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("personalInfo", (new Gson()).toJson(personalInfo));
        result.put("company", company.getName());

        ArrayList<HashMap<String, String>> sellLogs = new ArrayList<>();
        for (SellLog sellLog : listOfSellLogs) {
            sellLogs.add(sellLog.convertToHashMap());
        }
        result.put("listOfSellLogs", (new Gson()).toJson(sellLogs));

        HashMap<String, String> products = new HashMap<>();
        for (Product product : availableProducts.keySet()) {
            products.put(product.getId(), availableProducts.get(product).getId());
        }
        result.put("availableProducts", (new Gson()).toJson(products));

        ArrayList<String> offs = new ArrayList<>(listOfOffs.keySet());
        result.put("listOfOffs", (new Gson()).toJson(offs));

        result.put("balance", "" + balance);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        personalInfo = (new Gson()).fromJson(theMap.get("personalInfo"), PersonalInfo.class);
        company = Market.getInstance().getCompanyByName(theMap.get("company"));

        listOfSellLogs = new ArrayList<>();
        ArrayList<HashMap<String, String>> sellLogs = (new Gson()).fromJson(theMap.get("listOfSellLogs"), new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType());
        for (HashMap<String, String> hashMap : sellLogs) {
            SellLog sellLog = new SellLog();
            sellLog.setFieldsFromHashMap(hashMap);
            listOfSellLogs.add(sellLog);
        }

        availableProducts = new HashMap<>();
        HashMap<String, String> products = (new Gson()).fromJson(theMap.get("availableProducts"), new TypeToken<HashMap<String, String>>(){}.getType());
        for (String productId : products.keySet()) {
            availableProducts.put(Market.getInstance().getProductById(productId),
                    Market.getInstance().getProductSellInfoById(products.get(productId)));
        }

        listOfOffs = new HashMap<>();
        ArrayList<String> offs = (new Gson()).fromJson(theMap.get("listOfOffs"), new TypeToken<ArrayList<String>>(){}.getType());
        for (String offId : offs) {
            listOfOffs.put(offId, Market.getInstance().getOffById(offId));
        }

        balance = Integer.parseInt(theMap.get("balance"));
    }

    public SellLog getSellLogById(String id) {
        for (SellLog sellLog : listOfSellLogs) {
            if (sellLog.getMainLog().getId().equals(id)) {
                return sellLog;
            }
        }
        return null;
    }
}
