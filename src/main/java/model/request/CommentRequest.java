package model.request;

import model.Comment;

import java.util.HashMap;

public class CommentRequest extends Request {
    private Comment comment;

    public CommentRequest(Comment comment) {
        super();
        this.comment = comment;
    }

    public CommentRequest(String id) {
        super(id);
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

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> result = super.convertToHashMap();
//        result.put("comment", comment.convertToHashMap());
        return result;
    }

    @Override
    public void setFieldsFromHashMap(HashMap<String, String> theMap) {
        super.setFieldsFromHashMap(theMap);
        comment = new Comment();
//        comment.setFieldsFromHashMap((HashMap<String, Object>) theMap.get("comment"));
    }
}
