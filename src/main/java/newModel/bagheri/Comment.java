package newModel.bagheri;

public class Comment {
    private final String userId;
    private final String productId;
    private final String title;
    private final String content;
    private CommentStatus commentStatus;
    private final boolean didUserBuy;

    public Comment(String userId, String productId, String title, String content, boolean didUserBuy) {
        this.userId = userId;
        this.productId = productId;
        this.title = title;
        this.content = content;
        this.didUserBuy = didUserBuy;
        commentStatus = CommentStatus.WAITING_FOR_APPROVAL;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
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

    enum CommentStatus {
        WAITING_FOR_APPROVAL,
        APPROVED,
        NOT_APPROVED_BY_ADMIN
    }
}