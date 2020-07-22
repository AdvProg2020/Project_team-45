package server.newModel.bagheri.wallet;

import server.model.user.User;

public class BuyerWallet extends Wallet{
    private int usableBalance;

    public BuyerWallet(User user) {
        super(user);
        this.usableBalance = 0;
    }

    public int getUsableBalance() {
        return usableBalance;
    }

    public void increaseBalance(int amount) {
        this.balance += amount;
        this.usableBalance += amount;
    }

    public boolean changeUsableBalance(int amount) {
        int newUsableBalance = usableBalance + amount;
        if(usableBalance >=0 && usableBalance <= balance) {
            usableBalance = newUsableBalance;
            return true;
        }
        return false;
    }
}