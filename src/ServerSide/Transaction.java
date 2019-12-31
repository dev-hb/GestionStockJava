package ServerSide;

public class Transaction {
    private int id;
    private Bank bank;
    private String date;

    public Transaction(int id, Bank bank, String date) {
        this.id = id;
        this.bank = bank;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", bank=" + bank +
                ", date='" + date + '\'' +
                '}';
    }
}
