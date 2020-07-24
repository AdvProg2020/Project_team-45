package server.newModel.bagheri.wallet;

import server.model.user.User;

public class SellerWallet extends Wallet{
    private static int leastAmount = 10;

    public SellerWallet(User user) {
        super(user);
    }

    public static int getLeastAmount() {
        return leastAmount;
    }

    public static void setLeastAmount(int leastAmount) {
        SellerWallet.leastAmount = leastAmount;
    }

    //
    public void increaseBalance(int amount) {
        this.balance += amount;
    }

    public boolean decreaseBalance(int amount) {
        if (this.balance - amount < leastAmount)
            return false;
        this.balance -= amount;
        return true;
    }
}