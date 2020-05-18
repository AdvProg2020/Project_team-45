package model.request;

import model.Comment;

public class CommentRequest extends Request {
    private final Comment comment;

    public CommentRequest(Comment comment) {
        super();
        this.comment = comment;
    }

    @Override
    public void apply() {
        comment.approveComment();
        comment.getProduct().addApprovedComment(comment);
    }

    @Override
    public String getType() {
        return "new comment";
    }

    @Override
    public void decline() {
        comment.declineComment();
        super.decline();
    }

    @Override
    public String toString() {
        return super.toString() + '\n' + "comment:" + comment;
    }
}
