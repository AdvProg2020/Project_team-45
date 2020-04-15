package model.user;

import model.Cart;

public class AnonymousUser {
    private Cart cart;

    public AnonymousUser() {
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }
}
