package ServerSide;

public class Account {
    private int id;
    private Bank bank;
    private double sold;

    public Account(int id, Bank bank, double sold) {
        this.id = id;
        this.bank = bank;
        this.sold = sold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", bank=" + bank +
                ", sold=" + sold +
                '}';
    }
}
