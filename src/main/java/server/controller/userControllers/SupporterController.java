package server.controller.userControllers;

import server.controller.ServerManager;
import server.controller.managers.Manager;
import server.model.user.Buyer;
import server.newModel.bagheri.Massage;
import server.newModel.bagheri.Supporter;
import server.newModel.bagheri.chatRoom.DoubleChatRoom;

import java.util.ArrayList;
import java.util.HashMap;

public class SupporterController extends UserController implements Manager {
    private static final SupporterController instance = new SupporterController();
    private final ServerManager serverManager = ServerManager.getInstance();

    private SupporterController() {
    }

    public static SupporterController getInstance() {
        return instance;
    }

    public HashMap<String, ArrayList<String>> getActiveSupporterAllActiveChats() {
        HashMap<String, ArrayList<String>> activeUserAllActiveChats = new HashMap<>();
        for (DoubleChatRoom activeChat : ((Supporter) UserController.getActiveUser()).getActiveChats()) {
            activeUserAllActiveChats.put(activeChat.getBuyer().getUsername(), activeChat.getAllMassagesText());
        }
        return activeUserAllActiveChats;
    }

    public ArrayList<String> getOnlineSupporters() {
        ArrayList<String> onlineSupporters = new ArrayList<>();
        for (String username : serverManager.getOnlineUsernames()) {
            if(market.getUserByUsername(username).getRole().equals("supporter"))
                onlineSupporters.add(username);
        }
        return onlineSupporters;
    }

    public void startNewChatForActiveBuyer(String username) {
        Supporter supporter = (Supporter) market.getUserByUsername(username);
        Buyer buyer = (Buyer) UserController.getActiveUser();
        DoubleChatRoom offerChatRoom = buyer.getActiveChat();
        if (offerChatRoom != null) {
            offerChatRoom.inactivate();
            supporter.removeChat(offerChatRoom);
        }
        DoubleChatRoom newChatRoom = new DoubleChatRoom(supporter, buyer);
        supporter.addNewChat(newChatRoom);
        buyer.setActiveChat(newChatRoom);
    }

    public void addMassageForBuyer(String massageText) {
        Buyer buyer = (Buyer) UserController.getActiveUser();
        buyer.getActiveChat().addMassage(new Massage(buyer, massageText));
        //TODO: update Supporter
    }

    public void addMassageForSupporter(String massageText, String buyerUsername) {
        Supporter supporter = (Supporter) UserController.getActiveUser();
        Massage massage = new Massage(supporter, massageText);
        supporter.getChatByBuyerUsername(buyerUsername).addMassage(massage);
        //TODO: update Buyer
    }

    public ArrayList<String> getMessages() {
        return ((Buyer) UserController.getActiveUser()).getActiveChat().getAllMassagesText();
    }

    @Override
    public Object getItemById(String Id) {
        return null;
    }
}
