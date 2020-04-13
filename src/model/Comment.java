package model;

public class Comment {
    private Buyer userCommended;
    private Product product;
    private String title;
    private String content;
    private CommendStatus condition;
    private boolean didUserBuy;

    public Comment(Buyer userCommended, Product product, String title, String content, boolean didUserBuy) {
        this.userCommended = userCommended;
        this.product = product;
        this.title = title;
        this.content = content;
        this.didUserBuy = didUserBuy;
    }

    public void approvingComment () {

    }

    public void notAproveingComment () {

    }

    @Override
    public String toString() {
        return "Comment{" +
                "userCommended=" + userCommended +
                ", product=" + product +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", condition=" + condition +
                ", didUserBuy=" + didUserBuy +
                '}';
    }

    enum CommendStatus{
        WAITING_FOR_APPROVAL,
        APPROVED,
        NOT_APPROVED_BY_ADMIN;
    }
}