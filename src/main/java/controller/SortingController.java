package controller;

import model.product.Product;

import java.util.ArrayList;
import java.util.Comparator;

public class SortingController {
    private static final SortingController instance = new SortingController();
    private final ArrayList<String> sortTypes;
    private String activeSort;

    private SortingController() {
        sortTypes = new ArrayList<>();
        sortTypes.add("seen");
        sortTypes.add("sell");
        sortTypes.add("popularity");
        sortTypes.add("price increase");
        sortTypes.add("price decrease");
        sortTypes.add("time");
        activeSort = "seen";
    }

    public static SortingController getInstance() {
        return instance;
    }

    public boolean setActiveSort(String inputSort) {
        if (sortTypes.contains(inputSort)) {
            activeSort = inputSort;
            return true;
        }
        return false;
    }

    public void disableCurrentSort() {
        // TODO : bagheri use it
        activeSort = "seen";
    }

    public void sortingProducts(ArrayList<Product> productsList) {
        switch (activeSort) {
            case "seen":
                productsList.sort((product1, product2) -> product2.getSeen() - product1.getSeen());
                break;
            case "sell":
                productsList.sort((product1, product2) -> product2.getSellCount() - product1.getSellCount());
                break;
            case "popularity":
                productsList.sort((product1, product2) -> Float.compare(product2.getAverageScore(), product1.getAverageScore()));
                break;
            case "price increase":
                productsList.sort(Comparator.comparingInt(Product::getMinimumPrice));
                break;
            case "price decrease":
                productsList.sort((product1, product2) -> product2.getMinimumPrice() - product1.getMinimumPrice());
                break;
            case "time":
                productsList.sort((product1, product2) -> product2.getProductionDate().compareTo(product1.getProductionDate()));
                break;
        }
    }
}