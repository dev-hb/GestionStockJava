package SalesManagement;

import DatabaseConnection.DataConnection;
import ProductManagement.Product;
import ProductManagement.ProductDAOIMPL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LigneVenteDAOIMPL implements LigneVenteDAO{
    
    private DataConnection dc;
    PreparedStatement pstm;
    VenteDAOIMPL daoVente = new VenteDAOIMPL();
    ProductDAOIMPL daoProduct = new ProductDAOIMPL();

    public LigneVenteDAOIMPL() {
        dc = DataConnection.getConnection();
    }

    @Override
    public LigneVente find(int id) {

        try {
            String url = "SELECT lignevente.id as id, designation, prix, id_vente, id_produit, qte FROM lignevente INNER JOIN produit ON produit.id=id_produit WHERE lignevente.id=?";

            pstm = dc.conn.prepareStatement(url);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next() == false) {
                return null;
            } else {
                LigneVente flag;
                do {
                    Vente v = daoVente.find(rs.getInt("id_vente"));
                    Product p = daoProduct.find(rs.getInt("id_produit"));
                    flag = new LigneVente(id, v, p, rs.getInt("qte"));
                } while (rs.next());
                return flag;
            }

        } catch (Exception eee) {
            eee.printStackTrace();
        }
        return null;

    }

    @Override
    public void create(LigneVente p) {

        try {

            String query = "INSERT INTO lignevente (id_vente, id_produit, qte) VALUES (?, ?, ?)";
            pstm = dc.conn.prepareStatement(query);
            pstm.setInt(1, p.getVente().getId());
            pstm.setInt(2, p.getProduit().getId());
            pstm.setInt(3, p.getQte());
            int rows = pstm.executeUpdate();
            System.out.println("Ligne de Vente inséré !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(LigneVente p) {
        try {
            String query = "DELETE FROM lignevente WHERE id = ?";
            pstm = dc.conn.prepareStatement(query);
            pstm.setInt(1, p.getId());
            int rows = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(LigneVente p) {
        try {
            String query = "UPDATE lignevente SET id_produit=?, qte=? WHERE id=?";
            pstm = dc.conn.prepareStatement(query);
            pstm.setInt(1, p.getProduit().getId());
            pstm.setInt(2, p.getQte());
            pstm.setInt(3, p.getId());
            int x = pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<LigneVente> findAll() {

        List<LigneVente> ventes = new ArrayList<>();
        try {
            String query = "SELECT * FROM lignevente";
            pstm = dc.conn.prepareStatement(query);
            ResultSet rs;
            rs = pstm.executeQuery();
            LigneVente v;
            while (rs.next()) {
                Product p = daoProduct.find(rs.getInt("id_produit"));
                Vente vl = daoVente.find(rs.getInt("id_vente"));
                v = new LigneVente(rs.getInt("id"), vl, p, rs.getInt("qte"));
                ventes.add(v);
            }
            return ventes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    
    @Override
    public List<LigneVente> findAll(int id) {

        List<LigneVente> ventes = new ArrayList<>();
        try {
            String query = "SELECT lignevente.id as id, designation, prix, qte, id_produit, id_vente FROM lignevente INNER JOIN produit ON produit.id=id_produit WHERE id_vente=?";
            pstm = dc.conn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs;
            rs = pstm.executeQuery();
            LigneVente v;
            while (rs.next()) {
                Product p = daoProduct.find(rs.getInt("id_produit"));
                Vente vl = daoVente.find(rs.getInt("id_vente"));
                v = new LigneVente(rs.getInt("id"), vl, p, rs.getInt("qte"));
                ventes.add(v);
            }
            return ventes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    
}
