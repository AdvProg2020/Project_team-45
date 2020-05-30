package model.user;

public class AnonymousUser implements CartHolder {
    private final Cart cart;

    public AnonymousUser() {
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }
}
