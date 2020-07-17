package server.newModel.bagheri;


import server.model.user.User;

public class Wallet {
    private static int leastAmount = 0;
    private final User user;
    private int amount;

    public Wallet(User user, int amount) {
        this.user = user;
        this.amount = amount;
    }

    public static int getLeastAmount() {
        return leastAmount;
    }

    public User getUser() {
        return user;
    }

    public int getAmount() {
        return amount;
    }

    public static void setLeastAmount(int leastAmount) {
        Wallet.leastAmount = leastAmount;
    }

    public void increaseAmount(int amount) {
        this.amount += amount;
    }

    public boolean decreaseAmount(int amount) {
        if (this.amount - amount < leastAmount)
            return false;
        this.amount -= amount;
        return true;
    }
}
