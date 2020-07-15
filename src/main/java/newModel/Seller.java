package newModel;

public class Seller extends User {
    private int companyId;
    private int balance;

    public Seller(int companyId) {
        super(); // for personal info TODO
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
