package server.newModel.bagheri;

import java.util.ArrayList;

public class ChatRoom {
    boolean isActive;
    ArrayList<Massage> allMassages;

    public ChatRoom() {
        isActive = true;
    }

    public ArrayList<Massage> getAllMassages() {
        return allMassages;
    }

    public ArrayList<String> getAllMassagesText() {
        ArrayList<String> allMassagesText = new ArrayList<>();
        for (Massage massage : allMassages) {
            allMassagesText.add(massage.toString());
        }
        return allMassagesText;
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