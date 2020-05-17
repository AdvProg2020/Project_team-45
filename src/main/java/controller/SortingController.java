package controller;

import model.Product;

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
//        sortTypes.add("time");
        activeSort = "seen";
    }

    public static SortingController getInstance() {
        return instance;
    }

    public ArrayList<String> getAvailableSorts() {
        return new ArrayList<>(sortTypes);
    }

    public boolean setActiveSort(String inputSort) {
        if (sortTypes.contains(inputSort)){
            activeSort = inputSort;
            return true;
        }
        return false;
    }

    public String showCurrentSort() {
        return activeSort;
    }

    public void disableCurrentSort() {
        activeSort = "seen";
    }

    public void sortingProducts(ArrayList<Product> productsList) {
        if (activeSort.equals("seen")) {
            productsList.sort((product1, product2) -> product2.getSeen() - product1.getSeen());
        } else if (activeSort.equals("sell")) {
            productsList.sort((product1, product2) -> product2.getSellCount() - product1.getSellCount());
        } else if (activeSort.equals("popularity")) {
            productsList.sort((product1, product2) -> Float.compare(product2.getAverageScore(), product1.getAverageScore()));
        } else if (activeSort.equals("price increase")) {
            productsList.sort(Comparator.comparingInt(Product::getMinimumPrice));
        } else if (activeSort.equals("price decrease")) {
            productsList.sort((product1, product2) -> product2.getMinimumPrice() - product1.getMinimumPrice());
        } else{
            //sortingByTime(productsList);
        }
    }

//    private void sortingBySeen(ArrayList<Product> productsList) {
//        productsList.sort((product1, product2) -> product2.getSeen() - product1.getSeen());
//    }
//
//    private void sortingBySell(ArrayList<Product> productsList) {
//        productsList.sort((product1, product2) -> product2.getSellCount() - product1.getSellCount());
//    }
//
//    private void sortingByPopularity(ArrayList<Product> productsList) {
//        productsList.sort((product1, product2) -> Float.compare(product2.getAverageScore(), product1.getAverageScore()));
//    }
//
//    private void sortingByPriceIncrease(ArrayList<Product> productsList) {
//        productsList.sort(Comparator.comparingInt(Product::getMinimumPrice));
//    }
//
//    private void sortingByPriceDecrease(ArrayList<Product> productsList) {
//        productsList.sort((product1, product2) -> product2.getMinimumPrice() - product1.getMinimumPrice());
//    }
//
//    private void sortingByTime(ArrayList<Product> productsList) {
//        productsList.sort((product1, product2) -> );
//    }
}
