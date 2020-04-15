package model.request;

import model.Comment;

public class CommentRequest extends Request {
    private Comment comment;

    public CommentRequest(String requestId, Comment comment) {
        super(requestId);
        this.comment = comment;
    }

    @Override
    public void apply() {}
}
