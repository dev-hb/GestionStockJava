package ServerSide;

import java.util.List;

public interface TransactionDAO {

    public Transaction find(int id);

    public void create(Transaction p);

    public void delete(Transaction p);

    public void update(Transaction p);

    public List<Transaction> findAll();
}
