package ProductManagement;

import CategoryManagement.Category;
import CategoryManagement.CategoryDAOIMPL;
import DatabaseConnection.DataConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOIMPL implements ProductDAO {
     private DataConnection dc;
     PreparedStatement pstm;
     CategoryDAOIMPL dao;
     public ProductDAOIMPL(){
         dc =DataConnection.getConnection();
         dao = new CategoryDAOIMPL();
     }

    @Override
    public Product find(int id) {
 
           
            try{
                String url = "SELECT * FROM produit WHERE id=?";
            
            pstm=dc.conn.prepareStatement(url);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            // recherche de la catégorie
            
            if(rs.next() == false ){
                return null;
            }else{
                 Product flag;
                do{
                  Category res = dao.find(rs.getInt("catid"));
                  flag =new Product(id , rs.getString("designation") , rs.getDouble("prix"), res);
                 }while(rs.next());
                return flag;
            } 
             
               
            
            }catch(Exception eee){
               eee.printStackTrace();
            }
         return null;
       
    }

    @Override
    public void create(Product p) {
       
    try{      
        
            
            String query = "INSERT INTO produit VALUES(?,?,?,?)";
            pstm = dc.conn.prepareStatement(query);
            
            pstm.setInt(1,p.getId());
            pstm.setString(2, p.getDesignation());
            pstm.setDouble(3, p.getPrix());
            int id = p.getCatid().getId();
            pstm.setInt(4, id);
             int rows = pstm.executeUpdate();
             System.out.println("Produit inséré !");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product p) {
        try{         
            String query = "DELETE FROM produit WHERE id = ?";
            pstm = dc.conn.prepareStatement(query);
            pstm.setInt(1, p.getId());
             int rows = pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product p, String des , double price,Category category) {
        try{ 
            String query = "UPDATE produit SET designation=?,prix=?,catid=? WHERE id=?";
            pstm = dc.conn.prepareStatement(query);
            pstm.setString(1, des);
            pstm.setDouble(2, price);
            pstm.setInt(3, category.getId());
            pstm.setInt(4, p.getId());
            int x = pstm.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    private Category findCategory(int id){
        String query= "SELECT * FROM categorie WHERE id = ?";
        try{
        pstm = dc.conn.prepareStatement(query);
        pstm.setInt(1,id);   
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
         
        return new Category(rs.getInt("id") , rs.getString("name") , rs.getString("description"));   
        }else{
            return null;
        }
        }catch(SQLException dd){
            dd.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<Product> findAll() {
       
       List<Product> produits = new ArrayList<>();
         try{     
            String query = "SELECT * FROM produit";
            pstm = dc.conn.prepareStatement(query);
            ResultSet rs;
            rs =  pstm.executeQuery();
            while(rs.next()){
                Category c= findCategory(rs.getInt("catid"));
                produits.add(new Product(rs.getInt("id") , rs.getString("designation"), rs.getDouble("prix"),c));
            }
            return produits;
         }catch(SQLException e){
            e.printStackTrace();
        }
         return null;
        
    }

    @Override
    public List<Product> findAll(String key) {
        ResultSet rs = null; 
       List<Product> produits = null;
         try{         
            produits = new ArrayList<>();
            String query = "SELECT * FROM produit WHERE designation= ?";
            
            pstm = dc.conn.prepareStatement(query);
            pstm.setString(1, key);
            rs =  pstm.executeQuery();
            while(rs.next()){
                Category c = dao.findProduct(rs.getInt("id"));
                produits.add(new Product(rs.getInt("id") , rs.getString("designation"), rs.getDouble("prix"),c));
            }
         }catch(SQLException e){
            e.printStackTrace();
        }
         
        if(produits.size() !=0){
            return produits;
        }else{
            return null;
        }
    }
    public List<Category> getCategories(){
        List<Category> categories =  dao.findAll();
        return categories;
    }
     
    public Category findCate(String key) {
             
         try{      
            String query = "SELECT * FROM categorie WHERE name=?";
            
            pstm = dc.conn.prepareStatement(query);
            pstm.setString(1, key);
            ResultSet rs =  pstm.executeQuery();
            if(!rs.next()){
             
            Category categorie = new Category(rs.getInt("id") , rs.getString("name"), rs.getString("description"));
               
            return categorie;   
            }
         }catch(SQLException e){
            e.printStackTrace();
        }
         
        return null;
    }
        public Category getProductCategory(int id){
            String query = "SELECT category.name FROM produit inner join category on produit.id_category = category.id WHERE produit.id=?";
            try{
              pstm = dc.conn.prepareStatement(query);
              ResultSet rs = pstm.executeQuery();
              return new Category(rs.getInt("id") , rs.getString("name"),rs.getString("description"));
            }catch(SQLException dd){
                dd.printStackTrace();
            }
            return null;
        }
     
     
}
