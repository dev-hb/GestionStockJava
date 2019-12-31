package PaiementManagement;

import SalesManagement.Vente;

import java.io.Serializable;

public class Paiement implements Serializable {

    private int id;
    private Vente vente;
    private double montant;
    private String date;
    private String proprietaire;
    private String dateEffet;
    private TypePaiement type;

    public Paiement(int id, Vente vente, double montant, String date, String proprietaire, String dateEffet, TypePaiement type) {
        this.id = id;
        this.vente = vente;
        this.montant = montant;
        this.date = date;
        this.proprietaire = proprietaire;
        this.dateEffet = dateEffet;
        this.type = type;
    }

    public Paiement(Vente vente, double montant, String date, String proprietaire, String dateEffet, TypePaiement type) {
        this.id = 0;
        this.vente = vente;
        this.montant = montant;
        this.date = date;
        this.proprietaire = proprietaire;
        this.dateEffet = dateEffet;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(String dateEffet) {
        this.dateEffet = dateEffet;
    }

    public TypePaiement getType() {
        return type;
    }

    public void setType(TypePaiement type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Paiement{" + "id=" + id + ", vente=" + vente + ", montant=" + montant + ", date=" + date + ", propietaire=" + proprietaire + ", dateEffet=" + dateEffet + ", type=" + type + '}';
    }

}
