package ProductManagement;

import CategoryManagement.Category;
import java.util.*;

public interface ProductDAO {
    public Product find(int id);
    public void create(Product p);
    public void delete(Product p);
    public void update(Product p,String s, double price,Category categorie);
    public List<Product> findAll();
    public List<Product> findAll(String key);
}
