package ServerSide;

import PaiementManagement.Paiement;

import java.io.Serializable;

public class Transaction implements Serializable {
    private int id;
    private Paiement paiement;
    private String date;

    public Transaction(int id, Paiement paiement, String date) {
        this.id = id;
        this.paiement = paiement;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
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
                ", paiement=" + paiement +
                ", date='" + date + '\'' +
                '}';
    }
}
