package CategoryManagement;

import DatabaseConnection.DataConnection;
import ProductManagement.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOIMPL implements CategoryDAO {

    private DataConnection dc;

    PreparedStatement pstm;

    public CategoryDAOIMPL() {
        dc = DataConnection.getConnection();

    }

    @Override
    public Category find(int id) {
        try {
            String url = "SELECT * FROM categorie WHERE id=?";
            pstm = dc.conn.prepareStatement(url);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next() == false) {
                return null;
            } else {
                Category flag;
                do {
                    flag = new Category(id, rs.getString("name"), rs.getString("description"));
                } while (rs.next());
                return flag;
            }
        } catch (Exception eee) {
            eee.printStackTrace();
        }
        return null;

    }

    @Override
    public void create(Category ca) {
        try {
            String query = "INSERT INTO categorie (name, description) VALUES(?,?)";
            pstm = dc.conn.prepareStatement(query);
            pstm.setString(1, ca.getName());
            pstm.setString(2, ca.getDescription());
            int rows = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category ca) {
        try {
            String query = "DELETE FROM categorie WHERE id = ?";
            pstm = dc.conn.prepareStatement(query);
            pstm.setLong(1, ca.getId());
            int rows = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category ca, String name, String description) {
        try {
            String query = "UPDATE categorie SET name=?,description=? WHERE id=?";
            pstm = dc.conn.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, description);
            pstm.setLong(3, ca.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try {
            String query = "SELECT * FROM categorie";
            pstm = dc.conn.prepareStatement(query);
            ResultSet rs;
            rs = pstm.executeQuery();
            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Category findProduct(int id) {
        List<Category> res = findAll();
        for (Category c : res) {
            for (Product p : c.getProducts()) {
                if (p.getId() == id) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public Category findCate(String key) {
        try {
            String url = "SELECT * FROM categorie WHERE name=?";
            pstm = dc.conn.prepareStatement(url);
            pstm.setString(1, key);
            ResultSet rs = pstm.executeQuery();
            if (rs.next() == false) {
                return null;
            } else {
                Category flag;
                do {
                    flag = new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
                } while (rs.next());
                return flag;
            }
        } catch (Exception eee) {
            eee.printStackTrace();
        }
        return null;
    }

}
