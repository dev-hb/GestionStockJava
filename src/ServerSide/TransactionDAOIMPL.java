package ServerSide;

import DatabaseConnection.DataConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDAOIMPL implements TransactionDAO {
    private DataConnection dc;

    PreparedStatement pstm;

    public TransactionDAOIMPL(){
        dc = DataConnection.getConnection();
    }

    @Override
    public Transaction find(int id) {
        return null;
    }

    @Override
    public void create(Transaction p) {
        try {
            String query = "INSERT INTO transaction (id_paiement, date_trans) VALUES(?,?)";
            pstm = dc.conn.prepareStatement(query);
            pstm.setInt(1, p.getPaiement().getId());
            pstm.setString(2, p.getDate());
            int rows = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Transaction p) {

    }

    @Override
    public void update(Transaction p) {

    }

    @Override
    public List<Transaction> findAll() {
        return null;
    }
}
