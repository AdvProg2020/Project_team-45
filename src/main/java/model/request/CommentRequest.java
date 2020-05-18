package model.request;

import model.Comment;

import java.util.HashMap;

public class CommentRequest extends Request {
    private Comment comment;

    public CommentRequest(HashMap<String, String> fieldsAndValues) {
        super(fieldsAndValues);
    }

    public Comment getComment() {
        return comment;
    }

    @Override
    public void apply() {}

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
