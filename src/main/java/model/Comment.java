package model;

import com.google.gson.Gson;
import model.product.Product;
import model.user.User;

import java.util.HashMap;

public class Comment implements Savable {
    private User user;
    private Product product;
    private String title;
    private String content;
    private CommentStatus commentStatus;
    private boolean didUserBuy;

    public Comment(User user, Product product, String title, String content, boolean didUserBuy) {
        this.user = user;
        this.product = product;
        this.title = title;
        this.content = content;
        this.didUserBuy = didUserBuy;
        this.commentStatus = CommentStatus.WAITING_FOR_APPROVAL;
    }

    public Comment() {
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public CommentStatus getCommentStatus() {
        return commentStatus;
    }

    public boolean isDidUserBuy() {
        return didUserBuy;
    }

    public boolean isApprovedComment() {
        return commentStatus.equals(CommentStatus.APPROVED);
    }

    public void approveComment() {
        this.commentStatus = CommentStatus.APPROVED;
    }

    public void declineComment() {
        this.commentStatus = CommentStatus.NOT_APPROVED_BY_ADMIN;
    }

    public String showComment() {
        return "title: " + title + '\n' + "content: " + content;
    }

    @Override
    public String toString() {
        return "user=" + user + '\n' +
                "product: " + product + '\n' +
                "title: " + title + '\n' +
                "content: " + content + '\n' +
                "commentStatus: " + commentStatus + '\n' +
                "didUserBuy: " + didUserBuy;
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put("user", user.getId());
        result.put("product", product.getId());
        result.put("title", title);
        result.put("content", content);
        result.put("commentStatus", (new Gson()).toJson(commentStatus));
        result.put("didUserBuy", "" + didUserBuy);
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        Market market = Market.getInstance();
        user = market.getUserById(theMap.get("user"));
        product = market.getProductById(theMap.get("product"));
        title = theMap.get("title");
        content = theMap.get("content");
        commentStatus = (new Gson()).fromJson(theMap.get("commentStatus"), CommentStatus.class);
        didUserBuy = Boolean.parseBoolean(theMap.get("didUserBuy"));
    }

    enum CommentStatus {
        WAITING_FOR_APPROVAL,
        APPROVED,
        NOT_APPROVED_BY_ADMIN
    }
}