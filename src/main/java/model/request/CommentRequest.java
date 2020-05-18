package model.request;

import model.Comment;

import java.util.HashMap;

public class CommentRequest extends Request {
    private final Comment comment;

    public CommentRequest(Comment comment) {
        super();
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    @Override
    public void apply() {

    }

    @Override
    public String getType() {
        return "new comment";
    }

    @Override
    public String toString() {
        return super.toString() +
                "comment:" + comment;
    }
}
