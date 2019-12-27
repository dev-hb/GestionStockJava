package CategoryManagement;

import ProductManagement.Product;
import ProductManagement.ProductDAOIMPL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String name;
    private String description;
    private List<Product> produits;
    
    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.produits = getProducts();
    }
    
    public List<Product> getProducts(){
         return this.produits;
    }
    
    public void setProduits(List<Product> produits){
        this.produits = produits;
    }
    
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String toString(){
        return name;
    }
     
}
