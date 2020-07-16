package newModel;

public class Seller extends User {
    private int companyId;
    private int balance;

    public Seller(String id, boolean justId, int companyId) {
        super(id, justId);
        this.companyId = companyId;
        this.balance = 0;

    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getBalance() {
        return balance;
    }

    public void increaseBalance(int amount) {
        this.balance += amount;
    }
}
