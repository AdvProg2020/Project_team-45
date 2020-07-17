package newModel.bagheri;

import model.user.User;

public class Massage {
    User sender;
    String content;

    public Massage(User sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }
}