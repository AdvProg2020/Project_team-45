package server.newModel.bagheri;

import java.util.ArrayList;

public class ChatRoom {
    boolean isActive;
    ArrayList<Massage> allMassages;

    public ChatRoom() {
        isActive = true;
    }

    public boolean addMassage(Massage newMassage) {
        if (!isActive)
            return false;
        allMassages.add(newMassage);
        return true;
    }

    public void inactivate() {
        isActive = false;
    }
}