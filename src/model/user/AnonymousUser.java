package model.user;

import model.Cart;

public class AnonymousUser {
    private Cart cart;

    public AnonymousUser() {
        cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }
}
