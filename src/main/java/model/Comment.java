package model;

import model.user.User;

public class Comment {
    ;
    private final User user;
    private final Product product;
    private final String title;
    private final String content;
    private CommentStatus commentStatus;
    private final boolean didUserBuy;

    public Comment(User user, Product product, String title, String content, boolean didUserBuy) {
        this.user = user;
        this.product = product;
        this.title = title;
        this.content = content;
        this.didUserBuy = didUserBuy;
        this.commentStatus = CommentStatus.WAITING_FOR_APPROVAL;
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

    public void approveComment () {
        this.commentStatus = CommentStatus.APPROVED;
    }

    public void declineComment () {
        this.commentStatus = CommentStatus.NOT_APPROVED_BY_ADMIN;
    }

    enum CommentStatus {
        WAITING_FOR_APPROVAL,
        APPROVED,
        NOT_APPROVED_BY_ADMIN
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
}