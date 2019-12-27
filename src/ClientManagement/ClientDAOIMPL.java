package ClientManagement;

import CategoryManagement.Category;
import DatabaseConnection.DataConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOIMPL implements ClientDao{
     private DataConnection dc;
     
     PreparedStatement pstm;
     
     public ClientDAOIMPL(){
          dc = DataConnection.getConnection();
     }

    @Override
    public Client find(int id) {
try{
            String url = "SELECT * FROM client WHERE id=?";
            pstm=dc.conn.prepareStatement(url);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next() == false ){
                return null;
            }else{
                 Client flag;
                do{
                  flag =new Client(id , rs.getString("nom") , rs.getString("prenom") , rs.getString("adresse"),rs.getString("ville"));
                 }while(rs.next());
                return flag;
            } 
            }catch(Exception eee){
               eee.printStackTrace();
            }
         return null;
    }

    @Override
    public void create(Client c) {
                try{      
            String query = "INSERT INTO client VALUES(?,?,?,?,?)";
            pstm = dc.conn.prepareStatement(query);
            pstm.setInt(1, c.getId());
            pstm.setString(2, c.getNom());
            pstm.setString(3, c.getPrenom());
            pstm.setString(4, c.getAdresse());
            pstm.setString(5,c.getVille());
            int rows = pstm.executeUpdate();
            System.out.println("Client inséré !");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Client c) {
          try{         
            String query = "DELETE FROM client WHERE id = ?";
            pstm = dc.conn.prepareStatement(query);
            pstm.setLong(1, c.getId());
             int rows = pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client c, String nom, String prenom, String adresse, String ville) {
         try{ 
            String query = "UPDATE client SET nom=?,prenom=?,adresse=?,ville=? WHERE id=?";
            pstm = dc.conn.prepareStatement(query);
            pstm.setString(1, nom);
            pstm.setString(2, prenom);
            pstm.setString(3, adresse);
            pstm.setString(4, ville);
            pstm.setInt(5, c.getId());
            pstm.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<Client> findAll() {
      List<Client> clients = new ArrayList<>();
         try{     
            String query = "SELECT * FROM client";
            pstm = dc.conn.prepareStatement(query);
            ResultSet rs;
            rs =  pstm.executeQuery();
            while(rs.next()){
                clients.add(new Client(rs.getInt("id") , rs.getString("nom"), rs.getString("prenom") , rs.getString("adresse"),rs.getString("ville")));
            }
            return clients;
         }catch(SQLException e){
            e.printStackTrace();
        }
         return null;
    }
     
     

}
