package server.newModel.bagheri;

import server.model.user.PersonalInfo;
import server.model.user.User;

import java.util.ArrayList;

public class Supporter extends User {
    private ArrayList<ChatRoom> allChats;
    private ArrayList<ChatRoom> activeChats;

    public Supporter(PersonalInfo personalInfo) {
        super(personalInfo);
        this.allChats = new ArrayList<>();
        this.activeChats = new ArrayList<>();
    }

    public Supporter(String id) {
        super(id);
    }

    @Override
    public String getRole() {
        return "supporter";
    }

    public ArrayList<ChatRoom> getActiveChats() {
        return activeChats;
    }

    public void addNewChat(ChatRoom newChat) {
        allChats.add(newChat);
        activeChats.add(newChat);
    }

    public void removeChat(ChatRoom newChat) {
        activeChats.remove(newChat);
    }
}