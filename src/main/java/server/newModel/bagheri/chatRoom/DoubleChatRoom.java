package server.newModel.bagheri.chatRoom;

import server.model.user.Buyer;
import server.newModel.bagheri.Supporter;

public class DoubleChatRoom extends ChatRoom{
    private final Supporter supporter;
    private final Buyer buyer;

    public DoubleChatRoom(Supporter supporter, Buyer buyer) {
        this.supporter = supporter;
        this.buyer = buyer;
    }

    public Supporter getSupporter() {
        return supporter;
    }

    public Buyer getBuyer() {
        return buyer;
    }
}
