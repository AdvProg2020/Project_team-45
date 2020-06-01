package model.user;

import com.sun.applet2.AppletParameters;
import model.Company;
import model.Market;
import model.Off;
import model.log.SellLog;
import model.product.Product;
import model.product.ProductSellInfo;

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
    public HashMap convertToHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("personalInfo", personalInfo);
        result.put("company", company.getName());

        ArrayList<HashMap> sellLogs = new ArrayList<>();
        for (SellLog sellLog : listOfSellLogs) {
            sellLogs.add(sellLog.convertToHashMap());
        }
        result.put("listOfSellLogs", sellLogs);

        HashMap<String, String> products = new HashMap<>();
        for (Product product : availableProducts.keySet()) {
            products.put(product.getId(), availableProducts.get(product).getId());
        }
        result.put("availableProducts", products);

        ArrayList<String> offs = new ArrayList<>(listOfOffs.keySet());
        result.put("listOfOffs", offs);

        result.put("balance", balance);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap theMap) {
        personalInfo = (PersonalInfo) theMap.get("personalInfo");
        company = Market.getInstance().getCompanyByName((String) theMap.get("company"));

        listOfSellLogs = new ArrayList<>();
        ArrayList<HashMap> sellLogs = (ArrayList<HashMap>) theMap.get("listOfSellLogs");
        for (HashMap hashMap : sellLogs) {
            SellLog sellLog = new SellLog();
            sellLog.setFieldsFromHashMap(hashMap);
            listOfSellLogs.add(sellLog);
        }

        availableProducts = new HashMap<>();
        HashMap<String, String> products = (HashMap<String, String>) theMap.get("availableProducts");
        for (String productId : products.keySet()) {
            availableProducts.put(Market.getInstance().getProductById(productId),
                    Market.getInstance().getSellInfoById(products.get(productId)));
        }

        listOfOffs = new HashMap<>();
        ArrayList<String> offs = (ArrayList<String>) theMap.get("listOfOffs");
        for (String offId : offs) {
            listOfOffs.put(offId, Market.getInstance().getOffById(offId));
        }

        balance = (int) theMap.get("balance");
    }
}
