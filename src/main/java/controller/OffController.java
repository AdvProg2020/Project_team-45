package controller;

import controller.managers.Printer;
import controller.userControllers.UserController;
import model.Market;
import model.Off;
import model.user.Seller;

import java.util.ArrayList;

public class OffController implements Printer {
    private static OffController instance = new OffController();

    private OffController() {

    }

    public static OffController getInstance() {
        return instance;
    }

    public Off createOff() {
        return null;
    }

    public void setFieldOfOff(Off off, String field, String value) {
    }

    public Off getOffById(String offId) {
        return null;
    }

    @Override
    public String getAllInListAsString() {
        Seller activeSeller = (Seller) UserController.getActiveUser();
        ArrayList<Off> sellerOffs = (ArrayList<Off>) activeSeller.getListOfOffs().values();
        StringBuilder offsListString = new StringBuilder("Id,discount amount\n");
        for (Off off : sellerOffs) {
            offsListString.append(off.getOffId() + "," + off.getDiscountAmount() + "\n");
        }
        return offsListString.toString();
    }

    @Override
    public String getDetailStringById(String Id) {
        // should refactor // TODO : not now
        return null;
    }

    @Override
    public Off getItemById(String Id) {
        return Market.getInstance().getOffById(Id);
    }
}
