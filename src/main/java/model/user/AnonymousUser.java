package model.user;

public class AnonymousUser implements CartHolder {
    private Cart cart;

    public AnonymousUser() {
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
