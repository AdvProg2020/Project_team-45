package newModel.bagheri;

import model.user.PersonalInfo;
import model.user.User;

import java.util.ArrayList;

public class Supporter extends User {
    private ArrayList<ChatRoom> allChats;

    public Supporter(PersonalInfo personalInfo) {
        super(personalInfo);
        this.allChats = new ArrayList<>();
    }

    public Supporter(String id) {
        super(id);
    }

    @Override
    public String getRole() {
        return "supporter";
    }

    public void addNewChat(ChatRoom newChat) {
        allChats.add(newChat);
    }
}