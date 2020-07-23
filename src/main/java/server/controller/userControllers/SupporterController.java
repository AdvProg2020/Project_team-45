package server.controller.userControllers;

import server.controller.managers.Manager;
import server.newModel.bagheri.ChatRoom;
import server.newModel.bagheri.Supporter;

import java.util.ArrayList;

public class SupporterController extends UserController implements Manager {
    private static final SupporterController instance = new SupporterController();

    private SupporterController() {
    }

    public static SupporterController getInstance() {
        return instance;
    }

    public ArrayList<ArrayList<String>> getActiveUserAllActiveChats() {
        ArrayList<ArrayList<String>> activeUserAllActiveChats = new ArrayList<>();
        for (ChatRoom activeChat : ((Supporter) UserController.getActiveUser()).getActiveChats()) {
            activeUserAllActiveChats.add(activeChat.getAllMassagesText());
        }
        return activeUserAllActiveChats;
    }

    @Override
    public Object getItemById(String Id) {
        return null;
    }
}
