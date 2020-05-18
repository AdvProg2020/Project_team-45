package model;

import model.user.User;

public class Comment {
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
    }

    public User getUser() {
        return user;
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

    public void approveComment () {

    }

    public void declineComment () {

    }

    enum CommentStatus {
        WAITING_FOR_APPROVAL,
        APPROVED,
        NOT_APPROVED_BY_ADMIN
    }

    @Override
    public String toString() {
        return "title: " + title + '\n' + "content: " + content;
    }
}