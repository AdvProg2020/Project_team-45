package view;

import model.Cart;

public class CartManagingMenu extends ManagingMenu {
    private Cart cart;

    public CartManagingMenu(String name, Menu parent, Cart cart) {
        super(name, parent);
    }
}
