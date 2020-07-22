package server.newModel.nedaei.database.user;

import server.newModel.nedaei.database.DataBaseCompatible;

public class AnonymousUser extends DataBaseCompatible implements CartHolder {
    private int cartId;

    public AnonymousUser() {
    }

    public AnonymousUser(int id, boolean justId) {
        super(id, justId);
    }

    @Override
    protected void readFromDataBase() {

    }

    @Override
    protected void saveToDataBase() {

    }

    @Override
    public Cart getCart() {
        return null;
    }

    @Override
    public void setCart(Cart cart) {
    }
}
