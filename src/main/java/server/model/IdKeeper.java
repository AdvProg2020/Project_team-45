package server.model;

public class IdKeeper {
    private static IdKeeper instance = new IdKeeper();
    private int usersNewId;
    private int codedDiscountsNewId;
    private int categoriesNewId;
    private int productsNewId;
    private int logsNewId;
    private int offsNewId;
    private int requestsNewId;
    private int productSellInfosNewId;
    private int ratesNewId;
    private int auctionNewId;

    private IdKeeper() {
    }

    public static IdKeeper getInstance() {
        return instance;
    }

    public static void setInstance(IdKeeper instance) {
        IdKeeper.instance = instance;
    }

    public int getUsersNewId() {
        usersNewId++;
        return usersNewId;
    }

    public int getCodedDiscountsNewId() {
        codedDiscountsNewId++;
        return codedDiscountsNewId;
    }

    public int getCategoriesNewId() {
        categoriesNewId++;
        return categoriesNewId;
    }

    public int getProductsNewId() {
        productsNewId++;
        return productsNewId;
    }

    public int getLogsNewId() {
        logsNewId++;
        return logsNewId;
    }

    public int getOffsNewId() {
        offsNewId++;
        return offsNewId;
    }

    public int getRequestsNewId() {
        requestsNewId++;
        return requestsNewId;
    }

    public int getProductSellInfosNewId() {
        productSellInfosNewId++;
        return productSellInfosNewId;
    }

    public int getRatesNewId() {
        ratesNewId++;
        return ratesNewId;
    }

    public int getAuctionNewId() {
        auctionNewId++;
        return auctionNewId;
    }
}