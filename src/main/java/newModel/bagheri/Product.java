package newModel.bagheri;

import model.Comment;
import model.Company;
import model.category.FinalCategory;
import model.product.ProductSellInfo;
import model.product.Rate;
import model.user.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Product {
    private String name;
    private String companyId;
    private Date productionDate;
    private String productStatus;
    private ProductSellInfo defaultSellInfo;
    private int minimumPrice;
    private String categoryId;
    private LinkedHashMap<String, String> categoryFeatures;
    private String description;
    private float averageScore;
//    private final ArrayList<Comment> allComments;
//    private final ArrayList<Comment> approvedComments;
//    private final ArrayList<Rate> rates;
    private int sellCount;
    private int seen;
    private String imageAddress;
    private String videoAddress;


}
