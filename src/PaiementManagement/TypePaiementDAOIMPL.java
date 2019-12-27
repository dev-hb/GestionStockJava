package PaiementManagement;

import CategoryManagement.Category;
import DatabaseConnection.DataConnection;
import ProductManagement.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypePaiementDAOIMPL implements TypePaiementDAO {

    private DataConnection dc;

    PreparedStatement pstm;

    public TypePaiementDAOIMPL() {
        dc = DataConnection.getConnection();

    }

    @Override
    public TypePaiement find(int id) {
        try {
            String url = "SELECT * FROM typepaiement WHERE id=?";
            pstm = dc.conn.prepareStatement(url);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next() == false) {
                return null;
            } else {
                TypePaiement flag;
                do {
                    flag = new TypePaiement(id, rs.getString("name"));
                } while (rs.next());
                return flag;
            }
        } catch (Exception eee) {
            eee.printStackTrace();
        }
        return null;

    }

    @Override
    public void create(TypePaiement ca) {
        try {
            String query = "INSERT INTO typepaiement VALUES (?)";
            pstm = dc.conn.prepareStatement(query);
            pstm.setString(1, ca.getName());
            int rows = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TypePaiement ca) {
        try {
            String query = "DELETE FROM typepaiement WHERE id = ?";
            pstm = dc.conn.prepareStatement(query);
            pstm.setLong(1, ca.getId());
            int rows = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TypePaiement tp) {
        try {
            String query = "UPDATE typepaiement SET name=? WHERE id=?";
            pstm = dc.conn.prepareStatement(query);
            pstm.setString(1, tp.getName());
            pstm.setLong(2, tp.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<TypePaiement> findAll() {
        List<TypePaiement> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM typepaiement";
            pstm = dc.conn.prepareStatement(query);
            ResultSet rs;
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new TypePaiement(rs.getInt("id"), rs.getString("name")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
