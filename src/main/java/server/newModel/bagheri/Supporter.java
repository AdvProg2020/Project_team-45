package server.newModel.bagheri;

import server.model.user.PersonalInfo;
import server.model.user.User;
import server.newModel.bagheri.chatRoom.ChatRoom;
import server.newModel.bagheri.chatRoom.DoubleChatRoom;

import java.util.ArrayList;

public class Supporter extends User {
    private ArrayList<DoubleChatRoom> allChats;
    private ArrayList<DoubleChatRoom> activeChats;

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

    public ArrayList<DoubleChatRoom> getActiveChats() {
        return activeChats;
    }

    public void addNewChat(DoubleChatRoom newChat) {
        allChats.add(newChat);
        activeChats.add(newChat);
    }

    public void removeChat(ChatRoom newChat) {
        activeChats.remove(newChat);
    }

    public DoubleChatRoom getChatByBuyerUsername(String buyerUsername) {
        for (DoubleChatRoom activeChat : activeChats) {
            if (activeChat.getBuyer().getUsername().equals(buyerUsername))
                return activeChat;
        }
        return null;
    }
}