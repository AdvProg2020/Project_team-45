package model.request;

import model.Comment;

import java.util.HashMap;

public class CommentRequest extends Request {
    private Comment comment;

    public CommentRequest(HashMap<String, Object> fieldsAndValues) {
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
}
