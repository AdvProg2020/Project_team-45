package server.model.user;

import client.controller.BankController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.controller.P2PController;
import server.model.CodedDiscount;
import server.model.Market;
import server.model.Savable;
import server.model.log.BuyLog;
import server.model.log.Log;
import server.model.log.SellLog;
import server.model.product.Product;
import server.model.product.ProductSellInfo;
import server.model.product.Rate;
import server.network.BankSocket;
import server.newModel.bagheri.Auction;
import server.newModel.bagheri.chatRoom.ChatRoom;
import server.newModel.bagheri.chatRoom.DoubleChatRoom;
import server.newModel.bagheri.wallet.BuyerWallet;
import server.newModel.bagheri.wallet.SellerWallet;
import server.newModel.nedaei.FileProduct;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Buyer extends User implements CartHolder, Savable {
    private Cart cart;
    private ArrayList<CodedDiscount> listOfCodedDiscounts;
    private ArrayList<BuyLog> listOfBuyLogs;
    private HashMap<String, Rate> purchasedProducts; // productIds and rates

    private int accountNumber;
    private String accountToken;

    private BuyerWallet wallet;
    private HashMap<Auction, Integer> allAuctions;
    private DoubleChatRoom activeChat;

    public Buyer(PersonalInfo personalInfo) {
        super(personalInfo);
        this.cart = new Cart();
        this.listOfCodedDiscounts = new ArrayList<>();
        this.listOfBuyLogs = new ArrayList<>();
        this.purchasedProducts = new HashMap<>();
        accountNumber = BankSocket.createAccount(personalInfo.getFirstName(), personalInfo.getLastName(), personalInfo.getUsername(), personalInfo.getPassword());System.out.println(personalInfo.getUsername() + ": " + accountNumber);
        accountToken = BankSocket.getToken(personalInfo.getUsername(), personalInfo.getPassword());
        BankSocket.payReceipt(BankSocket.createDepositReceipt(accountToken, 1000, accountNumber));
        this.wallet = new BuyerWallet(this);
    }

    public Buyer(String id) {
        super(id);
        this.cart = new Cart();
    }

    public int getAccountBalance() {
        accountToken = BankSocket.getToken(personalInfo.getUsername(), personalInfo.getPassword());
        return BankSocket.getBalance(accountToken);
    }

    public void chargeWallet(int amount) throws Exception {
        if (getAccountBalance() < amount) {
            throw new Exception("not enough account balance");
        }
        accountToken = BankSocket.getToken(personalInfo.getUsername(), personalInfo.getPassword());
        BankSocket.payReceipt(BankSocket.createWithdrawReceipt(accountToken, amount, accountNumber));
        Market.getInstance().depositAccount(amount);
        wallet.increaseBalance(amount);
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public ArrayList<CodedDiscount> getListOfCodedDiscounts() {
        return listOfCodedDiscounts;
    }

    public int getBalance() {
        return wallet.getBalance();
    }

    public void setBalance(int balance) {
        wallet.setBalance(balance);
    }

    public ArrayList<BuyLog> getListOfBuyLogs() {
        return listOfBuyLogs;
    }

    public BuyLog getBuyLogById(String logId) {
        for (BuyLog buyLog : listOfBuyLogs) {
            if (buyLog.getMainLog().getId().equals(logId))
                return buyLog;
        }
        return null;
    }

    public boolean didBuyProduct(String productId) {
        for (String id : purchasedProducts.keySet()) {
            if (id.equals(productId)) {
                return true;
            }
        }
        return false;
    }

    public HashMap<String, Rate> getPurchasedProducts() {
        return purchasedProducts;
    }

    public CodedDiscount getDiscountByCode(String discountCode) {
        for (CodedDiscount discount : listOfCodedDiscounts) {
            if (discount.getCode().equalsIgnoreCase(discountCode)) {
                return discount;
            }
        }
        return null;
    }

    public boolean isDiscountCodeValid(String discountCode) {
        CodedDiscount discount = getDiscountByCode(discountCode);
        if (discount == null) {
            return false;
        }
        Date currentDate = new Date();
        return discount.getStartDate().compareTo(currentDate) <= 0 && discount.getEndDate().compareTo(currentDate) >= 0;
    }

    public void purchase(Log log) {
        for (ProductSellInfo sellInfo : getCart().getProductSellInfos()) {
            purchasedProducts.put(sellInfo.getProduct().getId(), null);
            sellInfo.getProduct().addSellCount();
            sellInfo.addSellCount();
            sellInfo.getAllBuyers().put(this, getCart().getProductAmountById(sellInfo.getId()));
            Seller seller = sellInfo.getSeller();
            SellLog sellLog = new SellLog(log, seller);
            seller.getListOfSellLogs().add(sellLog);
            seller.setBalance(seller.getBalance() + (sellLog.getIncome() * (100 - Market.getInstance().getMarketPercentage()))/100);
            Market.getInstance().depositAccount(sellLog.getIncome()*Market.getInstance().getMarketPercentage()/100);
            sellInfo.setStock(sellInfo.getStock() - cart.getProductAmountById(sellInfo.getId()));
        }
        BuyLog buyLog = new BuyLog(log);
        listOfBuyLogs.add(buyLog);

        log.calculateFinalPrice();
        if (log.getPurchaseMode().equals("wallet")) {
            setBalance(getBalance() - log.getFinalPrice());
        } if (log.getPurchaseMode().equals("account")) {
            withdrawAccount(log.getFinalPrice());
        }
        cart = new Cart();
    }

    public void auctionPurchase(Log log, int finalPrice) {
        ProductSellInfo sellInfo = log.getSellingProducts().get(0);
        purchasedProducts.put(sellInfo.getProduct().getId(), null);
        sellInfo.getProduct().addSellCount();
        sellInfo.addSellCount();
        sellInfo.getAllBuyers().put(this, 1);
        Seller seller = sellInfo.getSeller();
        SellLog sellLog = new SellLog(log, seller);
        seller.getListOfSellLogs().add(sellLog);

        seller.getSellerWallet().increaseBalance(finalPrice*(100 - Market.getInstance().getMarketPercentage())/100);
        Market.getInstance().depositAccount(finalPrice*Market.getInstance().getMarketPercentage()/100);

        sellInfo.setStock(sellInfo.getStock() - 1);
        BuyLog buyLog = new BuyLog(log);
        listOfBuyLogs.add(buyLog);

        wallet.increaseBalance(-finalPrice);
    }

    private void withdrawAccount(int finalPrice) {
        accountToken = BankSocket.getToken(personalInfo.getUsername(), personalInfo.getPassword());
        BankSocket.payReceipt(BankSocket.createWithdrawReceipt(accountToken, finalPrice, accountNumber));
    }

    public boolean canBuy() {
        return false;
    }

    public void rateProduct(Product product, Rate rate) {
    }

    @Override
    public String getRole() {
        return "buyer";
    }

    public void removeCodedDiscountFromList(CodedDiscount removingCodedDiscount) {
        listOfCodedDiscounts.remove(removingCodedDiscount);
    }

    public void addCodedDiscount(CodedDiscount addingDiscount) {
        listOfCodedDiscounts.add(addingDiscount);
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("personalInfo", (new Gson()).toJson(personalInfo));
        ArrayList<String> discounts = new ArrayList<>();
        for (CodedDiscount discount : listOfCodedDiscounts) {
            discounts.add(discount.getId());
        }
        result.put("listOfCodedDiscounts", (new Gson()).toJson(discounts));

        result.put("balance", "" + wallet.getBalance());

        ArrayList<HashMap<String, String>> buyLogs = new ArrayList<>();
        for (BuyLog buyLog : listOfBuyLogs) {
            buyLogs.add(buyLog.convertToHashMap());
        }
        result.put("listOfBuyLogs", (new Gson()).toJson(buyLogs));

        HashMap<String, String> products = new HashMap<>();
        System.out.println(purchasedProducts);
        for (String productId : purchasedProducts.keySet()) {
            products.put(productId, purchasedProducts.get(productId) == null ? "null" : purchasedProducts.get(productId).getId());
        }
        result.put("purchasedProducts", (new Gson()).toJson(products));

        result.put("accountNumber", "" + accountNumber);
        result.put("accountToken", accountToken);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        personalInfo = (new Gson()).fromJson(theMap.get("personalInfo"), PersonalInfo.class);
        ArrayList<String> codedDiscounts = (new Gson()).fromJson(theMap.get("listOfCodedDiscounts"), new TypeToken<ArrayList<String>>() {
        }.getType());
        listOfCodedDiscounts = new ArrayList<>();
        for (String id : codedDiscounts) {
            listOfCodedDiscounts.add(Market.getInstance().getCodedDiscountById(id));
        }

        wallet = new BuyerWallet(this);
        wallet.setBalance(Integer.parseInt((new Gson()).fromJson(theMap.get("balance"), String.class)));

        ArrayList<HashMap<String, String>> buyLogs = (new Gson()).fromJson(theMap.get("listOfBuyLogs"), new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType());
        listOfBuyLogs = new ArrayList<>();
        for (HashMap<String, String> hashMap : buyLogs) {
            BuyLog buyLog = new BuyLog();
            buyLog.setFieldsFromHashMap(hashMap);
            listOfBuyLogs.add(buyLog);
        }

        HashMap<String, String> products = (new Gson()).fromJson(theMap.get("purchasedProducts"), new TypeToken<HashMap<String, String>>() {
        }.getType());
        purchasedProducts = new HashMap<>();
        for (String productId : products.keySet()) {
            purchasedProducts.put(productId, Market.getInstance().getRateById(products.get(productId)));
        }

        accountNumber = Integer.parseInt(theMap.get("accountNumber"));
        accountToken = theMap.get("accountToken");
    }

    public void addAuction(Auction auction, int proposedPrice) {
        Integer previousPrice = allAuctions.get(auction);
        int amount = proposedPrice;
        if (previousPrice != null)
            amount -= previousPrice;
        wallet.changeUsableBalance(-amount);
        allAuctions.put(auction, proposedPrice);
    }

    public void removeAuction(Auction auction) {
        wallet.changeUsableBalance(allAuctions.remove(auction));
    }

    public BuyerWallet getWallet() {
        return wallet;
    }

    public void setActiveChat(DoubleChatRoom activeChat) {
        this.activeChat = activeChat;
    }

    public DoubleChatRoom getActiveChat() {
        return activeChat;
    }
}