package server.newModel.bagheri;

import server.newModel.DataBaseCompatible;

public class Comment extends DataBaseCompatible {
    private int userId;
    private int productId;
    private String title;
    private String content;
    private CommentStatus commentStatus;
    private boolean didUserBuy;

    public Comment(int userId, int productId, String title, String content, boolean didUserBuy) {
        this.userId = userId;
        this.productId = productId;
        this.title = title;
        this.content = content;
        this.didUserBuy = didUserBuy;
        commentStatus = CommentStatus.WAITING_FOR_APPROVAL;
    }

    public Comment(int id, boolean justId) {
        super(id, justId);
    }

    @Override
    protected void readFromDataBase() {

    }

    @Override
    protected void saveToDataBase() {

    }

//    public User getUser() {
//        return getUser(false);
//    }

//    public User getUser(boolean justId) {
//        if (justId) {
//            return new Admin(userId, true);
//        }
//        return ;
//    }

//    public Product getProduct() {
//        return product;
//    }

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

    enum CommentStatus {
        WAITING_FOR_APPROVAL,
        APPROVED,
        NOT_APPROVED_BY_ADMIN
    }
}