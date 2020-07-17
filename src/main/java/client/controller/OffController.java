package client.controller;

import client.controller.managers.Manager;
import server.model.Market;
import server.model.Off;
import server.model.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OffController implements Manager {
    private static final OffController instance = new OffController();
    private Off currentOff;

    private OffController() {
    }

    public static OffController getInstance() {
        return instance;
    }

    @Override
    public Off getItemById(String Id) {
        return Market.getInstance().getOffById(Id);
    }

    // TODO : edit come here

    public Off getCurrentOff() {
        return currentOff;
    }

    public void setCurrentOff(Off currentOff) {
        this.currentOff = currentOff;
    }

    public HashMap<String, String> getOffInfo(String offId) {
        Off off =  Market.getInstance().getOffById(offId);
        HashMap<String, String> offInfo = new HashMap<>();
        offInfo.put("startTime", String.valueOf(off.getStartTime()));
        offInfo.put("endTime", String.valueOf(off.getEndTime()));
        offInfo.put("discountAmount", String.valueOf(off.getDiscountAmount()));
        return offInfo;
    }

    public List<String> getProductsList(String viewingOffId) {
        return Market.getInstance().getOffById(viewingOffId).getProductsList().
                stream().map(Product::getName).collect(Collectors.toList());
    }
}
