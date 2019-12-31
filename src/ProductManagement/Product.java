package ProductManagement;

import CategoryManagement.Category;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String designation;
    private double prix;
    private Category catid;

    public Category getCatid() {
        return catid;
    }

    public void setCatid(Category categorie) {
        this.catid = categorie;
    }
    
    
    public Product(int d , String designation , double prix, Category categorie){
        this.id = d;
        this.designation = designation;
        this.prix = prix;
        this.catid = categorie;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getDesignation(){
        return this.designation;
    }
    
    public double getPrix(){
        return this.prix;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setDesignation(String des){
        this.designation = des;
    }
    
    public void setPrix(double prix){
        this.prix = prix;
    }

    @Override
    public String toString() {
        return designation;
    }
    
}

