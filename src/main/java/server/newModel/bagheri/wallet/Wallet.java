package server.newModel.bagheri.wallet;

import server.model.user.User;

public abstract class Wallet {
    protected final User user;
    protected int balance;

    public Wallet(User user) {
        this.user = user;
        this.balance = 0;
    }

    public User getUser() {
        return user;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public abstract void increaseBalance(int amount);
}
